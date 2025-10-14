package com.amangarg.samachar.common.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkHelperImpl(
    context: Context
) : NetworkHelper {
    private val appContext = context.applicationContext

    private val connectivityManager: ConnectivityManager
        get() = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isNetworkConnected(): Boolean {
        val cm = connectivityManager

        @Suppress("DEPRECATION")
        val networkInfo = cm.activeNetworkInfo
        @Suppress("DEPRECATION")
        return networkInfo?.isConnectedOrConnecting == true
    }
}
