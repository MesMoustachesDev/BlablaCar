package dev.blablacar.presentation.di

import dev.blablacar.presentation.rides.RidesActivityViewModel
import dev.blablacar.presentation.search.SearchActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModules = module {
    viewModel { SearchActivityViewModel(get(), get()) }
    viewModel { RidesActivityViewModel(get(), get()) }
}

val presentationModules =  viewModelModules