package com.example.vendorapp

import android.app.Application
import android.content.Context
import com.example.vendorapp.common.thread.AppCoroutineScope
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper
import kotlinx.coroutines.cancel

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Paper.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        AppCoroutineScope.io.cancel() // Cancel the coroutine scope when the app terminates
    }

    companion object {
        @get:Synchronized
        lateinit var instance: App
            private set

        val context: Context
            get() = instance.applicationContext
    }
}