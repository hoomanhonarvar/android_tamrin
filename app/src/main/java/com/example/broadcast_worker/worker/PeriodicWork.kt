package com.example.broadcast_worker.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWork(private val context: Context,
    workParams:WorkerParameters): Worker(context,workParams) {
        private var timeMiling
}