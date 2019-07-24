package dev.blablacar

import android.app.Application
import com.facebook.stetho.Stetho
import dev.blablacar.data.di.dataModules
import dev.blablacar.domain.di.useCaseModules
import dev.blablacar.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // Android context
            androidContext(this@MainApplication)
            // modules

            val appModules =
                dataModules + presentationModules  + useCaseModules
            modules(appModules)
        }

        if (dev.blablacar.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            //Do something
        }

        Stetho.initializeWithDefaults(this)
    }
}