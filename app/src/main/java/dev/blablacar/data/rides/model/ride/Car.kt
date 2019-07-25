package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("category")
    val category: String?,
    @SerializedName("comfort")
    val comfort: String?,
    @SerializedName("comfort_nb_star")
    val comfortNbStar: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("make")
    val make: String?,
    @SerializedName("model")
    val model: String?
)