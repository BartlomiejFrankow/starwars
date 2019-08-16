package com.example.movies.api

data class Movie(
    var id: Int,
    var title: String? = null,
    var year: String? = null,
    var genre: String? = null,
    var poster: String? = null
)