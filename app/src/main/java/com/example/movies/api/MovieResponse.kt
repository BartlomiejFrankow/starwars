package com.example.movies.api

data class MovieResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results : List<Movie>
)