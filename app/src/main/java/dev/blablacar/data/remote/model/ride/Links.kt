package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("_front")
    val front: String?,
    @SerializedName("_self")
    val self: String?
)