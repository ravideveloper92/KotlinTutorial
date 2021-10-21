package com.demo.products.datamodel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductInfoResponseItem(
    @SerializedName("displayText")
    val displayText: String,
    @SerializedName("distributorName")
    val distributorName: String,
    @SerializedName("manufacturerName")
    val manufacturerName: String,
    @SerializedName("mrp")
    val mrp: Double,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("productUrl")
    val productUrl: String,
    @SerializedName("ptr")
    val ptr: Double,
    @SerializedName("schemeLabelForRetailer")
    val schemeLabelForRetailer: String,
    @SerializedName("smartRecommendation")
    val smartRecommendation: Boolean,
    @SerializedName("stock")
    val stock: Int
):Parcelable