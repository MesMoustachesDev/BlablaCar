package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class DeparturePlace(
    @SerializedName("address")
    val address: String?,
    @SerializedName("city_name")
    val cityName: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)