package com.example.broadcast_worker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

import androidx.core.app.NotificationCompat
import com.example.broadcast_worker.R

class ForegroundService: Service(){
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start();
            Actions.STOP.toString() -> stop();
        }

        return super.onStartCommand(intent, flags, startId)
    }
    private fun start() {
        val notification = NotificationCompat.Builder(this, "service_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Service is Running")
            .setContentText("Here is the notification content")
            .build()
        startForeground(1, notification)
    }

    private fun stop() {
        stopSelf()
    }

    enum class Actions {
        START, STOP
    }
}