package dev.blablacar.data.rides.datasource

import dev.blablacar.data.rides.model.ride.Ride
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v2/trips")
    suspend fun getRides(
        @Query("_format") format: String = "json",
        @Query("locale") locale: String = "fr_FR",
        @Query("cur") currency: String = "EUR",
        @Query("fn") from: String,
        @Query("tn") to: String,
        @Query("page") page: Int = 1
    ): Ride
}