package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class RouteX(
    @SerializedName("distance_in_meters")
    val distanceInMeters: Int?,
    @SerializedName("type")
    val type: String?
)