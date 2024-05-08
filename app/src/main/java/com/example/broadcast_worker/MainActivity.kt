package com.example.broadcast_worker

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.broadcast_worker.ui.theme.Broadcast_workerTheme
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.broadcast_worker.reciever.network.NetworkReceiver
import com.example.broadcast_worker.reciever.network.NetworkStatus
import com.example.broadcast_worker.service.ForegroundNetworkService
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        val networkReceiver = NetworkReceiver(applicationContext)
        networkReceiver.observe().onEach {
            Log.i("network_hooman","status is $it")
        }.launchIn(lifecycleScope)

        setContent {
            val networkCallback by networkReceiver.observe().collectAsState(
                initial = NetworkStatus.UnAvailable)
            when(networkCallback){
                NetworkStatus.Loosing->{
                    sendBroadcast(Intent(applicationContext, ForegroundNetworkService::class.java)
                        .also {
                            it.action = ForegroundNetworkService.Actions.Losing.toString()
                            startService(it)})

                }
                NetworkStatus.Available->{
                    sendBroadcast(Intent(applicationContext, ForegroundNetworkService::class.java)
                        .also {
                            it.action = ForegroundNetworkService.Actions.Available.toString()
                            startService(it)})
                }
                NetworkStatus.Lost->{
                    sendBroadcast(Intent(applicationContext, ForegroundNetworkService::class.java)
                        .also {
                            it.action = ForegroundNetworkService.Actions.Lost.toString()
                            startService(it)})
                }
                NetworkStatus.UnAvailable->{
                    sendBroadcast(Intent(applicationContext, ForegroundNetworkService::class.java)
                        .also {
                            it.action = ForegroundNetworkService.Actions.UnAvailable.toString()
                            startService(it)})
                }
            }
            Broadcast_workerTheme {
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,){
                    Text("network status is :$networkCallback")
                }
            }
        }
    }

    override fun onDestroy() {
        sendBroadcast(Intent(applicationContext, ForegroundNetworkService::class.java)
            .also {
                it.action = ForegroundNetworkService.Actions.STOP.toString()
                startService(it)})
        super.onDestroy()

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