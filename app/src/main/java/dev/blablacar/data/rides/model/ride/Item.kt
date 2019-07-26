package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("is_selected")
    val isSelected: Boolean?,
    @SerializedName("value")
    val value: Boolean?
)