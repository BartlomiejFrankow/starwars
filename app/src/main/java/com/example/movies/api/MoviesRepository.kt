package com.example.movies.api

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

interface MoviesRepository {

    fun getMovies(): Single<MovieResponse>

}

class MoviesRepositoryImpl : MoviesRepository, KoinComponent {
    private val api: Api by inject()

    override fun getMovies(): Single<MovieResponse> {
        return api.getMovies().subscribeOn(Schedulers.io())
    }


}