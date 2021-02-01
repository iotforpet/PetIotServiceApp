package com.gratus.petservice.util.networkManager

import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkOnlineCheck @Inject constructor(private val connectivityManager: ConnectivityManager) :
    NetworkHelper {
    override val isNetworkOnline: Boolean
        get() = isConnected

    val isConnected: Boolean
        get() {
            val activeNetwork = connectivityManager.activeNetworkInfo
            return (activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting)
        }

}
