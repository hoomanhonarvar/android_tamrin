package com.example.broadcast_worker.worker

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class CustomWorker constructor(
    context_: Context,
    workerParameters: WorkerParameters,
): CoroutineWorker(context_, workerParameters) {
    private val special_context=context_
    override suspend fun doWork(): Result {
        if(isAirplaneModeOn(special_context) ){
            if(isBluetoothOn(special_context)){
                Log.d("worker_airplane", "AirPlane mode is ON and Bluetooth is ON ")
            }
            else{
                Log.d("worker_airplane", "AirPlane mode is ON and Bluetooth is OFF ")
            }
        }
        else{
            if(isBluetoothOn(special_context)){
                Log.d("worker_airplane", "AirPlane mode is OFF and Bluetooth is ON ")
            }
            else{
                Log.d("worker_airplane", "AirPlane mode is OFF and Bluetooth is OFF ")
            }
        }
        return Result.success()
    }

}
@Suppress("deprecation", "DEPRECATED_IDENTITY_EQUALS")
fun isAirplaneModeOn(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
        Settings.System.getInt(
            context.contentResolver,
            Settings.System.AIRPLANE_MODE_ON, 0
        ) !== 0
    } else {
        Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) !== 0
    }
}
@Suppress("deprecation", "DEPRECATED_IDENTITY_EQUALS")
fun isBluetoothOn(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
        Settings.System.getInt(
            context.contentResolver,
            Settings.System.BLUETOOTH_ON, 0
        ) !== 0
    } else {
        Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.BLUETOOTH_ON, 0
        ) !== 0
    }
}
