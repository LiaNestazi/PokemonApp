package com.example.domain.repository

import com.example.domain.models.PokemonDetails
import com.example.domain.models.RequestParams
import com.example.domain.models.ResponseResult

interface PokemonRepository {
    suspend fun getAllPokemons(requestParams: RequestParams): ResponseResult
    suspend fun getPokemonDetails(name: String): PokemonDetails
}