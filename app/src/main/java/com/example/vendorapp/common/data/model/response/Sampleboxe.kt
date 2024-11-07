package com.example.vendorapp.common.data.model.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Sampleboxe(
    @SerializedName("id")
    var id: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("pieces")
    var pieces: String?,
    @SerializedName("price")
    var price: String?
)