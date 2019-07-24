package dev.blablacar.data.user.repository

import dev.blablacar.data.remote.model.User

interface UserRepository {
    /**
     * Fetch user's animals
     */
//    suspend fun refreshToken(clientId: String,
//                            clientSecret: String)

    fun getUser(): User?

    fun setUser(newUser: User?)
}