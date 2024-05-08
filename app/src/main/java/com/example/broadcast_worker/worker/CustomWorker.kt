package com.example.broadcast_worker.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class CustomWorker constructor(
    context: Context,
    workerParameters: WorkerParameters,
): CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        delay(60000)
        Log.d("Custom Worker", "working!!")
        return Result.success()
    }
}
