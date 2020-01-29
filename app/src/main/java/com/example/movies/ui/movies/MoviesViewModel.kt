package com.example.movies.ui.movies

import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.example.movies.api.entities.Movie
import com.example.movies.api.entities.MovieResponse
import com.example.movies.api.repositories.MoviesRepository
import com.example.movies.application.App
import com.example.movies.base.BaseViewModel
import com.example.movies.module.UseCaseResult
import com.example.movies.room.entities.MoviesObj
import com.example.movies.room.entities.StarWarsDao
import com.example.movies.utils.EqualsDiffUtil
import kotlinx.coroutines.launch

class MoviesViewModel(val moviesRepository: MoviesRepository, val starWarsDao: StarWarsDao) :
    BaseViewModel<MoviesNavigator>() {

    var adapter = MovieAdapter(EqualsDiffUtil(), onItemClick = ::onMovieClicked)
    var showProgressBar = ObservableField(true)
    private val moviesFromDb = starWarsDao.getMovieObj()

    private fun onMovieClicked(movie: Movie) {
        Toast.makeText(App.appCtx(), movie.title, Toast.LENGTH_SHORT).show()
    }

    fun getMovies() {
        viewModelScope.launch {
            if (moviesFromDb == null) {

                when (val result = moviesRepository.getMovies()) {
                    is UseCaseResult.Success -> showListAndSaveData(result.data)
                    is UseCaseResult.Error -> showError(result.exception)
                }
            } else adapter.submitList(moviesFromDb.movies.results.toMutableList())
        }
    }

    private fun showError(it: Throwable) {
        Toast.makeText(App.appCtx(), it.message, Toast.LENGTH_SHORT).show()
        showProgressBar.set(false)
    }

    private fun showListAndSaveData(it: MovieResponse) {
        adapter.submitList(it.results.toMutableList())
        starWarsDao.upsetMovieObj(MoviesObj(movies = it))
        showProgressBar.set(false)
    }

}