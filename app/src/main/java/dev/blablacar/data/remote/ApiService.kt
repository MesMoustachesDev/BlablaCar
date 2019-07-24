package dev.blablacar.data.remote

import dev.blablacar.data.remote.model.ride.Ride
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v2/trips")
    suspend fun getEvents(
        @Query("_format") format: String = "json",
        @Query("locale") locale: String = "fr_FR",
        @Query("cur") currency: String = "EUR",
        @Query("fn") from: String,
        @Query("tn") to: String,
        @Query("page") page: Int = 1
    ): Ride
}