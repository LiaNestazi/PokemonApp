package com.example.data.repository

import com.example.data.api.Common
import com.example.data.api.models.HttpCall
import com.example.domain.models.PokemonDetails
import com.example.domain.models.RequestParams
import com.example.domain.models.ResponseResult
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.await

const val IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

class PokemonRepositoryImpl: PokemonRepository {
    override suspend fun getAllPokemons(requestParams: RequestParams): ResponseResult {
        val job = CoroutineScope(Dispatchers.IO).async {
            Common.retrofitService.getPokemonList(requestParams.offset, requestParams.limit).await()
        }
        return mapToDomain(httpCall = job.await(), requestParams = requestParams)
    }

    override suspend fun getPokemonDetails(name: String): PokemonDetails {
        val job = CoroutineScope(Dispatchers.IO).async {
            Common.retrofitService.getDetailsByName(name).await()
        }
        return job.await()
    }

    private fun mapToDomain(error: String? = null, httpCall: HttpCall, requestParams: RequestParams): ResponseResult{
        if (httpCall.results != null){
            httpCall.results!!.forEachIndexed { index, pokemon ->
                val imagePath = "${requestParams.offset+index+1}.png"
                pokemon.image_url = IMAGE_URL+imagePath
            }
        }
        return ResponseResult(errorMessage = error,
            count = httpCall.count,
            next = httpCall.next,
            previous = httpCall.previous,
            results = httpCall.results
        )
    }

}