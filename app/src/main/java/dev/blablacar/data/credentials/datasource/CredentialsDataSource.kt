package dev.blablacar.data.credentials.datasource

import dev.blablacar.data.credentials.datasource.model.Credentials

interface CredentialsDataSource {
    fun setCredentials(credentials: Credentials)
    fun getCredentials(): Credentials?
}
