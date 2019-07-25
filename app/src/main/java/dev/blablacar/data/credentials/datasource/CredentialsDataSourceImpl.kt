package dev.blablacar.data.credentials.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.blablacar.data.credentials.datasource.model.Credentials

class CredentialsDataSourceImpl(context: Context): CredentialsDataSource {
    private var sharedPref: SharedPreferences = context.getSharedPreferences(
        CREDENTIALS_DATA_SOURCE, Context.MODE_PRIVATE)

    override fun setCredentials(credentials: Credentials) {
        sharedPref.edit(true) {
            putString(CREDENTIALS_KEY, Gson().toJson(credentials))
        }
    }

    override fun getCredentials(): Credentials? {
        return try {
            Gson().fromJson(sharedPref.getString(CREDENTIALS_KEY, "{client_id: \"android-technical-tests\", client_secret: \"Y1oAL3QdPfVhGOWj3UeDjo3q02Qwhvrj\", grant_type =\"client_credentials\"}") ?: "", Credentials::class.java)
        } catch(jonSyntaxException: JsonSyntaxException) {
            null
        }
    }

    companion object {
        private const val CREDENTIALS_DATA_SOURCE = "CredentialsDataSource"
        private const val CREDENTIALS_KEY = "Credentials"
    }
}
