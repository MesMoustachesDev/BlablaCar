package dev.blablacar.data.credentials.repository

import dev.blablacar.data.credentials.datasource.CredentialsDataSource
import dev.blablacar.data.credentials.datasource.model.Credentials

class CredentialsRepositoryImpl(
    private val localDataSource: CredentialsDataSource
) : CredentialsRepository {
    override fun setCredentials(credentials: Credentials) {
        localDataSource.setCredentials(credentials)
    }

    override fun getCredentials(): Credentials? = localDataSource.getCredentials()
}