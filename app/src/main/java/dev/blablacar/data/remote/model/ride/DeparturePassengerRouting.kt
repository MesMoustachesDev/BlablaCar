package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class DeparturePassengerRouting(
    @SerializedName("proximity")
    val proximity: String?,
    @SerializedName("routes")
    val routes: List<Route?>?
)