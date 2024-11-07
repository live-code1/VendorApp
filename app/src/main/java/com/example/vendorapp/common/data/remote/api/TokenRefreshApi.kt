package com.example.vendorapp.common.data.remote.api

import androidx.annotation.Keep
import com.example.vendorapp.common.base.BaseApi
import com.example.vendorapp.common.data.model.response.CommonResponse
import retrofit2.http.GET
import retrofit2.http.Header

@Keep
interface TokenRefreshApi : BaseApi {

    @Keep
    @GET("refresh_token")
    override suspend fun refreshAccessToken(
        @Header("Authorization") auth: String
    ): CommonResponse

}