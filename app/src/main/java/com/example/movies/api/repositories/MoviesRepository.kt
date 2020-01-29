package com.example.movies.api.repositories

import com.example.movies.api.Api
import com.example.movies.api.entities.MovieResponse
import com.example.movies.module.UseCaseResult
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

interface MoviesRepository {

    suspend fun getMovies(): UseCaseResult<MovieResponse>

}

class MoviesRepositoryImpl : MoviesRepository, KoinComponent {
    private val api: Api by inject()

    override suspend fun getMovies(): UseCaseResult<MovieResponse> {
        return try {
            val result = api.getMovies().await()

            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            ex.printStackTrace()
            UseCaseResult.Error(ex)
        }
    }

}