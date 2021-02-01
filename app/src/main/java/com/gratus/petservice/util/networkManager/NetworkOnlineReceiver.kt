package com.gratus.petservice.util.networkManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.gratus.petservice.util.constants.AppConstants.Companion.IS_NETWORK_AVAILABLE
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_AVAILABLE_ACTION

class NetworkOnlineReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, arg1: Intent) {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = (activeNetwork != null
                && activeNetwork.isConnectedOrConnecting)
        val networkStateIntent = Intent(NETWORK_AVAILABLE_ACTION)
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnected)
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent)
    }
}