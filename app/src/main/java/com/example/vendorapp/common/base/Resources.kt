package com.example.vendorapp.common.base

import androidx.annotation.Keep

@Keep
sealed class Resource<out T> {
    @Keep
    data class Success<out T>(val value: T) : Resource<T>()

    @Keep
    data class Failure(
        val isNetworkError: Boolean?,
        val errorCode: Int?,
        val errorBody: Any?,
    ) : Resource<Nothing>()

    @Keep
    object Loading : Resource<Nothing>()

}