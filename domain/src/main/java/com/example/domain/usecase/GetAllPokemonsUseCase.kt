package com.example.domain.usecase

import com.example.domain.models.RequestParams
import com.example.domain.models.ResponseResult
import com.example.domain.repository.PokemonRepository

class GetAllPokemonsUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(requestParams: RequestParams): ResponseResult{
        return pokemonRepository.getAllPokemons(requestParams)
    }
}