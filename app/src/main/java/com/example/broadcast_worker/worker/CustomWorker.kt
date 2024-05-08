package com.example.broadcast_worker.worker

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class CustomWorker constructor(
    context: Context,
    workerParameters: WorkerParameters,
): CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        delay(20000)
        Log.d("Custom Worker", "working!!")
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
