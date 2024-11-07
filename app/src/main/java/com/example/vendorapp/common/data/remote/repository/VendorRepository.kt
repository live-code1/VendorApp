package com.example.vendorapp.common.data.remote.repository

import androidx.annotation.Keep
import com.example.vendorapp.common.data.remote.api.VendorApi
import com.example.vendorapp.common.preference.UserPreferences
import com.example.vendorapp.common.base.BaseRepository
import javax.inject.Inject

@Keep
class VendorRepository @Inject constructor(
    private val apiService: VendorApi,
    private val preferences: UserPreferences
) : BaseRepository(apiService) {

    // To get user profile data
    suspend fun getUserDetails(
        vendorId: Int?,
        lang: String?,
        userId: Int?,
        isAllowedSampling: Boolean?,
        isAllowedRental: Boolean?
    ) = safeApiCall {
        apiService.getUserDetails(
            "preferences.getAccessToken.first().toString()",
            vendorId, lang, userId, isAllowedSampling, isAllowedRental
        )
    }

}