package com.example.broadcast_worker.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log

class AirPlaneModeReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action==Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val airPlaneModeOn= Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            )!=0
            Log.i("Broadcast Receiver", "airplane mode: $airPlaneModeOn")
        }
    }
}