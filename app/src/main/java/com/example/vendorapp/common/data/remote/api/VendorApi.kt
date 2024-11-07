package com.example.vendorapp.common.data.remote.api

import androidx.annotation.Keep
import com.example.vendorapp.common.base.BaseApi
import com.example.vendorapp.common.data.model.response.CommonResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

@Keep
interface VendorApi : BaseApi {

    // For getting user data like full name, phone, gender, etc
    @GET("getVendorDetails")
    suspend fun getUserDetails(
        @Header("Authorization") auth: String,
        @Query("vendor_id") vendorId: Int?,
        @Query("lang") lang: String?,
        @Query("user_id") userId: Int?,
        @Query("is_allowed_sampling") isAllowedSampling: Boolean?,
        @Query("is_allowed_rental") isAllowedRental: Boolean?
    ): CommonResponse
}