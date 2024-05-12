package com.example.data.api

import com.example.data.api.models.HttpCall
import com.example.domain.models.Pokemon
import com.example.domain.models.PokemonDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {
    @GET(".")
    fun getPokemonList(@Query("offset") offset: Int,
                        @Query("limit") limit: Int
    ): Call<HttpCall>

    @GET("{name}")
    fun getDetailsByName(
        @Path("name") name: String
    ): Call<PokemonDetails>
}