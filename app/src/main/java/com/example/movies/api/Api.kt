package com.example.movies.api

import com.example.movies.api.entities.MovieResponse
import com.example.movies.module.HEADER_CONTENT_TYPE
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {

    @Headers(HEADER_CONTENT_TYPE)
    @GET("films/")
    fun getMovies(): Deferred<MovieResponse>

}