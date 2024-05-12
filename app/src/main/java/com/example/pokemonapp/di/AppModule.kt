package com.example.pokemonapp.di

import com.example.pokemonapp.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel>{
        MainViewModel(
            getAllPokemonsUseCase = get(),
            getPokemonDetailsUseCase = get(),
        )
    }
}