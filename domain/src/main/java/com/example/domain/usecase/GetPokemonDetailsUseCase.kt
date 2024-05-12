package com.example.domain.usecase

import com.example.domain.models.PokemonDetails
import com.example.domain.repository.PokemonRepository

class GetPokemonDetailsUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(name: String): PokemonDetails{
        return pokemonRepository.getPokemonDetails(name)
    }
}