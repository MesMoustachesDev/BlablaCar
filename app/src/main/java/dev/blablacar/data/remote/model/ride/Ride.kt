package dev.blablacar.data.remote.model.ride


import com.google.gson.annotations.SerializedName

data class Ride(
    @SerializedName("distance")
    val distance: Int?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("full_trips_count")
    val fullTripsCount: Int?,
    @SerializedName("has_bus")
    val hasBus: Boolean?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("lowest_price")
    val lowestPrice: Int?,
    @SerializedName("lowest_price_object")
    val lowestPriceObject: LowestPriceObject?,
    @SerializedName("pager")
    val pager: Pager?,
    @SerializedName("recommended_price")
    val recommendedPrice: Int?,
    @SerializedName("savings")
    val savings: Int?,
    @SerializedName("sorting_algorithm")
    val sortingAlgorithm: String?,
    @SerializedName("top_trips")
    val topTrips: List<Any?>?,
    @SerializedName("total_trip_count_to_display")
    val totalTripCountToDisplay: Int?,
    @SerializedName("trips")
    val trips: List<Trip?>?
)