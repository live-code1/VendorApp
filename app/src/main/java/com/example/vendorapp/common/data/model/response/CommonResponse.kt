package com.example.vendorapp.common.data.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class CommonResponse (

    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("message_code")
    var messageCode: String?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("success")
    var success: Boolean?

):java.io.Serializable