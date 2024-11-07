package com.example.vendorapp.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.annotation.Keep
import com.example.vendorapp.SafeClickListener

@Keep
class SafeClickUtils {

    companion object {
        fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
            val safeClickListener = SafeClickListener {
                onSafeClick(it)
            }
            setOnClickListener(safeClickListener)
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        // Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        // Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        // Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }
    }


}