package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class LowestPriceObject(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("string_value")
    val stringValue: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("value")
    val value: Int?
)