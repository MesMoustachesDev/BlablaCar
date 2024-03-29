package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class Commission(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("string_value")
    val stringValue: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("value")
    val value: Int?
)