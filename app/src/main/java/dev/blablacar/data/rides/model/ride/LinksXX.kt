package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class LinksXX(
    @SerializedName("_front")
    val front: String?,
    @SerializedName("_self")
    val self: String?
)