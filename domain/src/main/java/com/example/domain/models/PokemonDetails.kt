package com.example.domain.models

data class PokemonDetails(
    var errorMessage: String? = null,
    var name: String? = null,
    var base_experience: Int? = null,
    var height: Int? = null,
    var order: Int? = null,
    var weight: Int? = null,
    var abilities: MutableList<PokemonAbility>? = null,
    var forms: MutableList<PokemonForm>? = null,
    var stats: MutableList<PokemonStat>?= null
)
