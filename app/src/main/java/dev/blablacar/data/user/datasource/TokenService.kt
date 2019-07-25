package dev.blablacar.data.user.datasource

import dev.blablacar.data.credentials.datasource.model.Credentials
import dev.blablacar.data.user.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
    @POST("token")
    suspend fun getToken(
        @Body credentials: Credentials
    ): User
}