package dev.blablacar.data.rides.repository

import androidx.lifecycle.LiveData
import dev.blablacar.data.rides.model.ride.Trip

interface TripRepository {
    suspend fun fetchRides(
        start: String,
        stop: String,
        forceUpdate: Boolean = false,
        loadMore: Boolean = false
    )

    fun getRides(): LiveData<List<Trip>>
    fun isFullLoaded(): LiveData<Boolean>
}