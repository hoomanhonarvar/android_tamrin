package com.example.broadcast_worker.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel= NotificationChannel(
                "Network Status",
                "app notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}