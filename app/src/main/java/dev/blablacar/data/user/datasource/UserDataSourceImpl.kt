package dev.blablacar.data.user.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.blablacar.data.user.model.User

class UserDataSourceImpl(context: Context) : UserDataSource {
    private var sharedPref: SharedPreferences = context.getSharedPreferences(
        USER_DATA_SOURCE, Context.MODE_PRIVATE
    )

    override fun getUser(): User? = try {
        Gson().fromJson(sharedPref.getString(USER_KEY, "") ?: "", User::class.java)
    } catch (jonSyntaxException: JsonSyntaxException) {
        null
    }

    override fun setUser(user: User?) {
        sharedPref.edit(true) {
            putString(USER_KEY, Gson().toJson(user))
        }
    }

    companion object {
        private const val USER_DATA_SOURCE = "UserDataSource"
        private const val USER_KEY = "User"
    }
}
