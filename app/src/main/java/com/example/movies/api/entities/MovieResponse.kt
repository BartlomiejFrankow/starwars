package com.example.movies.api.entities

data class MovieResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results : List<Movie>
)