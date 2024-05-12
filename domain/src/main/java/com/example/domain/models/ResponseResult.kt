package com.example.domain.models

data class ResponseResult(
    var errorMessage: String? = null,
    var count: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var results: MutableList<Pokemon>? = null
)