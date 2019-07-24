package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class PriceWithCommission(
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