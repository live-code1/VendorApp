package com.example.vendorapp.common.base

import androidx.annotation.Keep

@Keep
abstract class BaseRepository(private val api: BaseApi) : SafeApiCall