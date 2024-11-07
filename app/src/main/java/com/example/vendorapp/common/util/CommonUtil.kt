package com.example.vendorapp.common.util

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.Keep
import com.example.vendorapp.R
import com.example.vendorapp.common.util.SafeClickUtils.Companion.setSafeOnClickListener

@Keep
class CommonUtil {
    companion object {

        /**
         * Checks network connectivity availability.
         * Supported SDKs:
         * - For API level 21 (Android 5.0, Lollipop) and higher: Uses ConnectivityManager#getNetworkCapabilities.
         * - For API level below 21: Uses ConnectivityManager#activeNetworkInfo (deprecated in API level 29).
         */
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager?.let {
                    val capabilities = it.getNetworkCapabilities(connectivityManager.activeNetwork)
                    capabilities?.let {
                        return it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                    }
                }
            } else {
                @Suppress("DEPRECATION")
                val networkInfo = connectivityManager.activeNetworkInfo
                networkInfo?.let {
                    return networkInfo.isConnected &&
                            (networkInfo.type == ConnectivityManager.TYPE_WIFI ||
                                    networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                }
            }
            return false
        }




        /*
        * Missing Internet Dialog
        * Which need context as param
        */
        fun showMissingInternetDialog(context: Context) {
            val dialog = Dialog(context, R.style.DialogThemeSize)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_missing_internet)
            val window: Window = dialog.window!!
            val wlp = window.attributes
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT
            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT
            wlp.gravity = Gravity.BOTTOM
            wlp.windowAnimations = R.style.bottomSheetAnimation
            window.attributes = wlp

            val btnAlright = dialog.findViewById(R.id.btnAlright) as Button

            btnAlright.setSafeOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }











    }
}