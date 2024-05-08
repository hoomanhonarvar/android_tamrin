package com.example.broadcast_worker

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.BackoffPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.broadcast_worker.ui.theme.Broadcast_workerTheme
import com.example.broadcast_worker.worker.CustomWorker
import java.time.Duration
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Broadcast_workerTheme {
                // A surface container using the 'background' color from the theme
                LaunchedEffect(key1 = Unit) {
                    val workRequest = PeriodicWorkRequestBuilder<CustomWorker>(
                        repeatInterval = 1,
                        repeatIntervalTimeUnit = TimeUnit.MINUTES,
                    ).setBackoffCriteria(
                        backoffPolicy = BackoffPolicy.LINEAR,
                        duration = Duration.ofSeconds(15)
                    ).build()

                    val workManager = WorkManager.getInstance(applicationContext)
                    workManager.enqueue(workRequest)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Broadcast_workerTheme {
        Greeting("Android")
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