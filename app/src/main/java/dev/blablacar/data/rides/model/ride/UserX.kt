package dev.blablacar.data.rides.model.ride


import com.google.gson.annotations.SerializedName

data class UserX(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("bbpro")
    val bbpro: Boolean?,
    @SerializedName("bbpro_segment")
    val bbproSegment: String?,
    @SerializedName("dialog")
    val dialog: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("encrypted_id")
    val encryptedId: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("grade")
    val grade: Int?,
    @SerializedName("has_picture")
    val hasPicture: Boolean?,
    @SerializedName("id_checked")
    val idChecked: String?,
    @SerializedName("links")
    val links: LinksXX?,
    @SerializedName("music")
    val music: String?,
    @SerializedName("pets")
    val pets: String?,
    @SerializedName("phone_hidden")
    val phoneHidden: Boolean?,
    @SerializedName("picture")
    val picture: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("rating_count")
    val ratingCount: Int?,
    @SerializedName("smoking")
    val smoking: String?,
    @SerializedName("uuid")
    val uuid: String?
)