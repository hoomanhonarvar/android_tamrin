package com.example.broadcast_worker.reciever.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkReceiver(context: Context) {
    val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager

    fun observe(): Flow<NetworkStatus> {
        return callbackFlow<NetworkStatus> {
            val callback=object :ConnectivityManager.NetworkCallback(){
                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(NetworkStatus.UnAvailable) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(NetworkStatus.Lost) }

                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(NetworkStatus.Loosing) }

                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(NetworkStatus.Available) }

                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose{
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()


    }
}

