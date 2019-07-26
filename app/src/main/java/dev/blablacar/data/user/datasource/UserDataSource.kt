package dev.blablacar.data.user.datasource

import dev.blablacar.data.user.model.User

interface UserDataSource {
    fun getUser(): User?
    fun setUser(user: User?)
}
