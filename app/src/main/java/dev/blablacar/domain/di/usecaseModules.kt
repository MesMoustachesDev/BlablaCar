package dev.blablacar.domain.di

import dev.blablacar.domain.usecase.GetRidesUseCase
import org.koin.dsl.module

val useCaseModules = module {
    factory { GetRidesUseCase(get()) }
}