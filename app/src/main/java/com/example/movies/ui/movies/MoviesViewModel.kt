package com.example.movies.ui.movies

import android.widget.Toast
import androidx.databinding.ObservableField
import com.example.movies.api.entities.Movie
import com.example.movies.api.entities.MovieResponse
import com.example.movies.api.repositories.MoviesRepository
import com.example.movies.application.App
import com.example.movies.base.BaseViewModel
import com.example.movies.room.entities.MoviesObj
import com.example.movies.room.entities.MoviesListObjDao
import com.example.movies.utils.EqualsDiffUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MoviesViewModel(val moviesRepository: MoviesRepository, val moviesListObjDao: MoviesListObjDao) : BaseViewModel<MoviesNavigator>() {

    private lateinit var subscription: Disposable

    var adapter = MovieAdapter(EqualsDiffUtil(), onItemClick = ::onMovieClicked)

    var showProgressBar = ObservableField(true)

    private fun onMovieClicked(movie: Movie) {
    }

    fun getMovies() {
        val moviesFromDb = moviesListObjDao.getAllMovies()
        if (moviesFromDb.isEmpty()) {
            subscription = moviesRepository.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { getMoviesSuccess(it) },
                    { getMoviesError(it) }
                )
        } else adapter.submitList(moviesFromDb[0].movies.results.toMutableList())
    }

    private fun getMoviesError(it: Throwable) {
        Toast.makeText(App.appCtx(), it.message, Toast.LENGTH_SHORT).show()
        showProgressBar.set(false)
    }

    private fun getMoviesSuccess(it: MovieResponse) {
        adapter.submitList(it.results.toMutableList())
        moviesListObjDao.upsetMovies(MoviesObj(movies = it))
        showProgressBar.set(false)
    }

    override fun onCleared() {
        subscription.dispose()
    }
}