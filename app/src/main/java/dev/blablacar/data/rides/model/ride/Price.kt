package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("price_color")
    val priceColor: String?,
    @SerializedName("string_value")
    val stringValue: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("value")
    val value: Float?
)