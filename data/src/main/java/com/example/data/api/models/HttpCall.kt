package com.example.data.api.models

import com.example.domain.models.Pokemon

data class HttpCall(
    var count: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var results: MutableList<Pokemon>? = null
)
