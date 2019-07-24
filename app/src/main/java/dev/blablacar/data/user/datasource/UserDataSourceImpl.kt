package dev.blablacar.data.user.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dev.blablacar.data.remote.model.User

class UserDataSourceImpl(context: Context) : UserDataSource {
    private var sharedPref: SharedPreferences = context.getSharedPreferences(
        "UserDataSourceImpl", Context.MODE_PRIVATE
    )

    override fun getUser(): User? = try {
        Gson().fromJson(sharedPref.getString("User", "") ?: "", User::class.java)
    } catch (jonSyntaxException: JsonSyntaxException) {
        null
    }

    override fun setUser(user: User?) {
        sharedPref.edit(true) {
            putString("User", Gson().toJson(user))
        }
    }
}
