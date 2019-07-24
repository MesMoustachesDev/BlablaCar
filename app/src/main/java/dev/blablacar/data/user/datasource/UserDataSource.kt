package dev.blablacar.data.user.datasource

import dev.blablacar.data.remote.model.User

interface UserDataSource {
    fun getUser(): User?
    fun setUser(user: User?)
}
