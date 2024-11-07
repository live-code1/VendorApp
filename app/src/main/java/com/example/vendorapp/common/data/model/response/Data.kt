package com.example.vendorapp.common.data.model.response



import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Data(
    @SerializedName("all_tray_list")
    var allTrayList: List<Any?>?,
    @SerializedName("banner")
    var banner: String?,
    @SerializedName("cart_count")
    var cartCount: String?,
    @SerializedName("categories")
    var categories: List<Category?>?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("has_added_tray")
    var hasAddedTray: String?,
    @SerializedName("has_tray_service")
    var hasTrayService: String?,
    @SerializedName("hide_timeslot")
    var hideTimeslot: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("is_tray_listing")
    var isTrayListing: String?,
    @SerializedName("logo")
    var logo: String?,
    @SerializedName("min_order_amount")
    var minOrderAmount: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("order_id")
    var orderId: String?,
    @SerializedName("samplebox_list")
    var sampleboxList: List<Any?>?,
    @SerializedName("sampleboxes")
    var sampleboxes: List<Sampleboxe?>?,
    @SerializedName("selected_date")
    var selectedDate: String?,
    @SerializedName("selected_timeslot")
    var selectedTimeslot: String?,
    @SerializedName("support_rental")
    var supportRental: String?,
    @SerializedName("support_sampling")
    var supportSampling: String?,
    @SerializedName("total_amount")
    var totalAmount: String?,
    @SerializedName("tray_list")
    var trayList: List<Any?>?
)