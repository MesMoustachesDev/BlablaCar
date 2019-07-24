package dev.blablacar.data.credentials.datasource.model

import com.google.gson.annotations.SerializedName

data class Credentials(
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String,
    @SerializedName("grant_type")
    val grantType: String = "client_credentials"
)