package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class ArrivalPassengerRouting(
    @SerializedName("proximity")
    val proximity: String?,
    @SerializedName("routes")
    val routes: List<RouteX?>?
)