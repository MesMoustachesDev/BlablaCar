package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class Facet(
    @SerializedName("items")
    val items: List<Item?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?
)