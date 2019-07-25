package dev.blablacar.data.rides.repository

import androidx.lifecycle.LiveData
import dev.blablacar.data.remote.model.ride.Trip

interface TripRepository {
    /**
     * Fetch user's animals
     */
    suspend fun fetchEvents(
        start: String,
        stop: String,
        forceUpdate: Boolean = false,
        loadMore: Boolean = false
    )

    fun getRides(): LiveData<List<Trip>>
    fun isFullLoaded(): LiveData<Boolean>
}