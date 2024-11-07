package com.example.vendorapp.common.thread

import androidx.annotation.Keep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
@Keep
object AppCoroutineScope {
    val io: CoroutineScope = CoroutineScope(Dispatchers.IO)
}