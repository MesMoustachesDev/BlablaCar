package dev.blablacar.data.user.repository

import androidx.lifecycle.LiveData
import dev.blablacar.data.remote.model.User
import dev.blablacar.data.user.datasource.UserDataSource

class UserRepositoryImpl(
    private val localDataSource: UserDataSource
) : UserRepository {

    override fun getUser(): User?  = localDataSource.getUser()

    override fun setUser(newUser: User?) {
        localDataSource.setUser(newUser)
    }
//    override suspend fun refreshToken(clientId: String,
//                                      clientSecret: String) {
//        if (fetchRunning) return
//            fetchRunning = true
//            withContext(Dispatchers.IO) {
//                try {
//                    val result = apiService.getToken(
//                        clientId,
//                        clientSecret
//                    )
//                    localDataSource.setUser(result)
//                } catch (error: Throwable) {
//                    throw error
//                } finally {
//                    fetchRunning = false
//                }
//            }
//    }
}