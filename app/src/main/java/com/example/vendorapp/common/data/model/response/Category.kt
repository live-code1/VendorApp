package com.example.vendorapp.common.data.model.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Category(
    @SerializedName("id")
    var id: String?,
    @SerializedName("link_category_id")
    var linkCategoryId: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("product_services")
    var productServices: List<ProductService?>?
)