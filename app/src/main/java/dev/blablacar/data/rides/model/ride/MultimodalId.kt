package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class MultimodalId(
    @SerializedName("id")
    val id: String?,
    @SerializedName("source")
    val source: String?
)