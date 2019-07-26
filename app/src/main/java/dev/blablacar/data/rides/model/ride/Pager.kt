package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class Pager(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("total")
    val total: Int?
)