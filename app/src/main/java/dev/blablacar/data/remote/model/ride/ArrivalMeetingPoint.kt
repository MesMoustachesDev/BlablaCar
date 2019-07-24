package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class ArrivalMeetingPoint(
    @SerializedName("address")
    val address: String?,
    @SerializedName("city_name")
    val cityName: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("tags")
    val tags: List<Any?>?
)