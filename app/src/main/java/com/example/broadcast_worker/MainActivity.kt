package com.example.broadcast_worker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.broadcast_worker.ui.theme.Broadcast_workerTheme
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.example.broadcast_worker.reciever.network.NetworkReceiver
import com.example.broadcast_worker.reciever.network.NetworkStatus
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkReceiver = NetworkReceiver(applicationContext)
        networkReceiver.observe().onEach {
            Log.i("network_hooman","status is $it")
        }.launchIn(lifecycleScope)


        setContent {
            val networkCallback by networkReceiver.observe().collectAsState(
                initial = NetworkStatus.UnAvailable)

            Broadcast_workerTheme {
                Text("$networkCallback")
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
}