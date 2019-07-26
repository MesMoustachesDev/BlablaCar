package dev.blablacar.data.user.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("client_id")
    val clientId: String?,
    @SerializedName("expires_in")
    val expiresIn: Int?,
    @SerializedName("issued_at")
    val issuedAt: Int?,
    @SerializedName("scopes")
    val scopes: List<String?>?,
    @SerializedName("token_type")
    val tokenType: String?
)