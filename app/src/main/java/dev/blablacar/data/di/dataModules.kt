package dev.blablacar.data.di

import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dev.blablacar.BuildConfig
import dev.blablacar.data.common.CacheStrategy
import dev.blablacar.data.credentials.datasource.CredentialsDataSource
import dev.blablacar.data.credentials.datasource.CredentialsDataSourceImpl
import dev.blablacar.data.credentials.repository.CredentialsRepository
import dev.blablacar.data.credentials.repository.CredentialsRepositoryImpl
import dev.blablacar.data.remote.ApiInterceptor
import dev.blablacar.data.remote.ApiService
import dev.blablacar.data.remote.model.ride.Trip
import dev.blablacar.data.rides.datasource.RideDataSource
import dev.blablacar.data.rides.datasource.RoomRidesDatabase
import dev.blablacar.data.rides.repository.TripRepository
import dev.blablacar.data.rides.repository.TripRepositoryImpl
import dev.blablacar.data.rides.repository.cache.RideCacheStrategy
import dev.blablacar.data.room.DataBase
import dev.blablacar.data.user.datasource.UserDataSource
import dev.blablacar.data.user.datasource.UserDataSourceImpl
import dev.blablacar.data.user.repository.UserRepository
import dev.blablacar.data.user.repository.UserRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repoModules = module {
    single { UserRepositoryImpl(get()) as UserRepository }
    single { CredentialsRepositoryImpl(get()) as CredentialsRepository }
    single { TripRepositoryImpl(get(), get(), get()) as TripRepository }
}

val cacheModules = module {
    single { RideCacheStrategy() as CacheStrategy<Trip> }
}

val netModule = module {
    single { StethoInterceptor() }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    single(named(NETWORK_API)) {
        val client = OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor(get(), get()))

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(get<StethoInterceptor>())
                .addNetworkInterceptor(get<HttpLoggingInterceptor>())
        }
        Retrofit.Builder()
            .client(client.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()

    }
    single { get<Retrofit>(named(NETWORK_API)).create(ApiService::class.java) as ApiService }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), DataBase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<DataBase>().eventDao() }

    single { RoomRidesDatabase(get()) as RideDataSource }

    single { CredentialsDataSourceImpl(get()) as CredentialsDataSource }
    single { UserDataSourceImpl(get()) as UserDataSource }
}

val dataModules = netModule + databaseModule + cacheModules + repoModules

const val NETWORK_API = "NETWORK_API"