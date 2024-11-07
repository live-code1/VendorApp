package com.example.vendorapp.common.data.model.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductService(
    @SerializedName("available_quantity")
    var availableQuantity: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("disable_qty_stepper")
    var disableQtyStepper: String?,
    @SerializedName("disclaimer_message")
    var disclaimerMessage: String?,
    @SerializedName("enable_gift_option")
    var enableGiftOption: String?,
    @SerializedName("final_price")
    var finalPrice: String?,
    @SerializedName("final_price_per_kg")
    var finalPricePerKg: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("is_addons_available")
    var isAddonsAvailable: String?,
    @SerializedName("is_allow_customer_comment")
    var isAllowCustomerComment: String?,
    @SerializedName("is_allowed_rental")
    var isAllowedRental: String?,
    @SerializedName("is_allowed_sampling")
    var isAllowedSampling: String?,
    @SerializedName("is_best_seller")
    var isBestSeller: String?,
    @SerializedName("is_show_disclaimer")
    var isShowDisclaimer: String?,
    @SerializedName("is_unavailable")
    var isUnavailable: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("new_arrival_end_time")
    var newArrivalEndTime: String?,
    @SerializedName("new_arrival_start_time")
    var newArrivalStartTime: String?,
    @SerializedName("product_type")
    var productType: String?,
    @SerializedName("quantity")
    var quantity: String?,
    @SerializedName("regular_price")
    var regularPrice: String?,
    @SerializedName("regular_price_per_kg")
    var regularPricePerKg: String?,
    @SerializedName("sample_type")
    var sampleType: String?,
    @SerializedName("service_duration")
    var serviceDuration: String?,
    @SerializedName("session_duration")
    var sessionDuration: String?,
    @SerializedName("supports_tray")
    var supportsTray: String?,
    @SerializedName("time_slot_quantity")
    var timeSlotQuantity: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("unit")
    var unit: String?,
    @SerializedName("vendor_id")
    var vendorId: String?,
    @SerializedName("vendor_name")
    var vendorName: String?,
    @SerializedName("weight")
    var weight: String?
)