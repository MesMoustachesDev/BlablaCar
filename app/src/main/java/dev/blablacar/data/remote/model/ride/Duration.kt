package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class Duration(
    @SerializedName("unity")
    val unity: String?,
    @SerializedName("value")
    val value: Int?
)