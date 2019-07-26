package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("distance_in_meters")
    val distanceInMeters: Int?,
    @SerializedName("type")
    val type: String?
)