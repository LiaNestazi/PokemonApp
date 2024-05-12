package com.example.data.api

object Common {
    private val URL = "https://pokeapi.co/api/v2/pokemon/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(URL).create(RetrofitServices::class.java)
}