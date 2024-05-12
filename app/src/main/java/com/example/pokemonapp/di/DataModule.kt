package com.example.pokemonapp.di

import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repository.PokemonRepository
import org.koin.dsl.module

val dataModule = module {
    single<PokemonRepository> {
        PokemonRepositoryImpl()
    }

}