package com.example.vendorapp.common.base

import androidx.annotation.Keep
import com.example.vendorapp.common.data.model.response.CommonResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

@Keep
interface BaseApi {
    // logout
    @Keep
    @POST("user-logout")
    suspend fun logout(@Header("Authorization") auth: String): CommonResponse

    // called whenever current get expired
    @GET("refresh_token")
    suspend fun refreshAccessToken(
        @Header("Authorization") auth: String
    ): CommonResponse
}