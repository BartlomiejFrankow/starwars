package com.example.movies.api

import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("filippella/Sample-API-Files/master/json/movies-api.json")
    fun getMovies(): Single<MovieResponse>

}