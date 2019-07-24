package dev.blablacar.presentation.di

import android.content.Intent
import dev.blablacar.presentation.home.RidesActivity
import dev.blablacar.presentation.home.RidesActivityViewModel
import dev.blablacar.presentation.routing.HomeScreen
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activityModules = module {
    factory {
        object : HomeScreen {
            override fun getIntent(): Intent = RidesActivity.createIntent(get())
        } as HomeScreen
    }
}

val viewModelModules = module {
    viewModel { RidesActivityViewModel(get(), get()) }
}

val presentationModules = activityModules + viewModelModules