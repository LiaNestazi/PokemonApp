package com.example.pokemonapp.di

import com.example.domain.usecase.GetAllPokemonsUseCase
import com.example.domain.usecase.GetPokemonDetailsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetAllPokemonsUseCase> {
        GetAllPokemonsUseCase(pokemonRepository = get())
    }
    factory<GetPokemonDetailsUseCase> {
        GetPokemonDetailsUseCase(pokemonRepository = get())
    }
}