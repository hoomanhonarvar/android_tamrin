package com.example.broadcast_worker.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat.startForeground
import com.example.broadcast_worker.R
import com.example.broadcast_worker.reciever.network.NetworkStatus

class ForegroundNetworkService:Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action){
            Actions.Available.toString()->Available()
            Actions.UnAvailable.toString()->UnAvailable()
            Actions.Lost.toString()->Lost()
            Actions.Losing.toString()->Losing()
            Actions.STOP.toString()->stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("ForegroundServiceType")
    private fun Losing() {
        val notification = NotificationCompat.Builder(this, "Network Status")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Network Status")
            .setContentText("your network status is :${NetworkStatus.Loosing}")
            .build()
        startForeground(1, notification)    }

    @SuppressLint("ForegroundServiceType")
    private fun Lost() {
        val notification = NotificationCompat.Builder(this, "Network Status")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Network Status")
            .setContentText("your network status is :${NetworkStatus.Lost}")
            .build()
        startForeground(1, notification)    }

    @SuppressLint("ForegroundServiceType")
    private fun UnAvailable() {
        val notification = NotificationCompat.Builder(this, "Network Status")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Network Status")
            .setContentText("your network status is :${NetworkStatus.UnAvailable}")
            .build()
        startForeground(1, notification)    }

    @SuppressLint("ForegroundServiceType")
    private fun Available() {
        val notification = NotificationCompat.Builder(this, "Network Status")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Network Status")
            .setContentText("your network status is :${NetworkStatus.Available}")
            .build()
        startForeground(1, notification)
    }


private fun stop(){
        stopSelf()
    }


    enum class Actions(){
        Available,STOP,UnAvailable,Lost,Losing
    }
}