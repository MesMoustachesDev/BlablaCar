package dev.blablacar.data.credentials.repository

import dev.blablacar.data.credentials.datasource.model.Credentials

interface CredentialsRepository {
    fun getCredentials(): Credentials?
    fun setCredentials(credentials: Credentials)
}