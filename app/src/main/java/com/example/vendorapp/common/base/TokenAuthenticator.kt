package com.example.vendorapp.common.base

import android.content.Context
import android.util.Log
import androidx.annotation.Keep
import com.example.vendorapp.common.data.model.response.CommonResponse
import com.example.vendorapp.common.data.remote.api.TokenRefreshApi
import com.example.vendorapp.common.preference.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

@Keep
class TokenAuthenticator @Inject constructor(
    context: Context,
    private val tokenApi: TokenRefreshApi,
) : Authenticator, BaseRepository(tokenApi) {
    private val appContext = context.applicationContext
    private val userPreferences = UserPreferences(appContext)

    @Keep
    override fun authenticate(route: Route?, response: Response): Request? {

        return runBlocking {
            when (val tokenResponse = getUpdatedToken()) {
                is Resource.Success -> {
//                    userPreferences.saveAccessAndRefreshToken(
//                        accessToken = "Bearer " + tokenResponse.value.data.accessToken,
//                        refreshToken = userPreferences.getRefreshToken.first().toString()
//                    )

                    //Log.e("token authenticator",tokenResponse.value.a)
                    response.request.newBuilder()
//                        .header("Authorization", "Bearer ${tokenResponse.value.data.accessToken}")
                        .build()
                }

                else -> null
            }
        }
    }

    @Keep
    private suspend fun getUpdatedToken(): Resource<CommonResponse> {
        val refreshToken = "Bearer ${userPreferences.getRefreshToken.first()}"
        Log.d("refreshToken", "getUpdatedToken: $refreshToken")
        return safeApiCall { tokenApi.refreshAccessToken(refreshToken) }
    }

}