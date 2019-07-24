package dev.blablacar.data.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dev.blablacar.BuildConfig
import dev.blablacar.data.credentials.repository.CredentialsRepository
import dev.blablacar.data.user.repository.UserRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInterceptor(
    private val credentialsRepository: CredentialsRepository,
    private val userRepository: UserRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val initialRequestWithoutHeaders = chain.request()
        val builder = initialRequestWithoutHeaders.newBuilder()
        setAuthHeader(builder)
        var initialRequest = builder.build()
        val response = chain.proceed(initialRequest)
        if (response.code() == 401) {//if unauthorized
            try {
                val credentials = credentialsRepository.getCredentials()
                credentials?.let {
                    runBlocking {
                        val newUser = getRetrofit()?.getToken(credentials)
                        userRepository.setUser(newUser)
                    }
                }
                setAuthHeader(builder) //set auth token to updated
                initialRequest = builder.build()
                return chain.proceed(initialRequest)
            } catch (e: Exception) {
                userRepository.setUser(null)
            }
        }
        return response
    }

    private fun getRetrofit(): TokenService? {
        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(StethoInterceptor())
                .addNetworkInterceptor(HttpLoggingInterceptor())
        }
        return Retrofit.Builder()
            .client(client.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()
            .create(TokenService::class.java)
    }

    private fun setAuthHeader(builder: Request.Builder) {
        userRepository.getUser()?.accessToken?.let {
            val bearer = "Bearer $it"
            builder.header(AUTHORIZATION_HEADER, bearer)
        }
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }
}