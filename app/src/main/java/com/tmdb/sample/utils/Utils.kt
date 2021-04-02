package com.tmdb.sample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class Utils {
    companion object {
        fun isConnected(context: Context): Boolean {
            var cm: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cm.run {
                        cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                            return when {
                                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                                else -> false
                            }
                        }
                    }
                } else {
                    return when (cm.activeNetworkInfo?.type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        else -> false
                    }
                }
            }
            return false
        }
    }
}