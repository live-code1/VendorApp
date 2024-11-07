package com.example.vendorapp.common.base

import androidx.annotation.Keep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

@Keep
interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {

                    is HttpException -> {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        val errorMessage = parseErrorMessage(errorBody) ?: ""
                        Resource.Failure(false, throwable.code(), errorMessage)
                    }

                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }

    private fun parseErrorMessage(errorBody: String?): String? {
        return try {
            val jsonObject = JSONObject(errorBody ?: "")
            jsonObject.optString("message", null)
        } catch (e: JSONException) {
            null
        }
    }
}