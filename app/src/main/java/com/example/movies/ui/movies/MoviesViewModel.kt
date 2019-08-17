package com.example.movies.ui.movies

import android.widget.Toast
import androidx.databinding.ObservableField
import com.example.movies.api.Movie
import com.example.movies.api.MovieResponse
import com.example.movies.api.MoviesRepository
import com.example.movies.application.App
import com.example.movies.base.BaseViewModel
import com.example.movies.utils.EqualsDiffUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MoviesViewModel(val moviesRepository: MoviesRepository) : BaseViewModel<MoviesNavigator>() {

    private lateinit var subscription: Disposable

    var adapter = MovieAdapter(EqualsDiffUtil(), onItemClick = ::onMovieClicked)

    var showProgressBar = ObservableField(true)

    private fun onMovieClicked(movie: Movie) {
    }

    fun getMovies() {
        subscription = moviesRepository.getMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getMoviesSuccess(it) },
                { getMoviesError(it) }
            )
    }

    private fun getMoviesError(it: Throwable) {
        Toast.makeText(App.appCtx(), it.message, Toast.LENGTH_SHORT).show()
    }

    private fun getMoviesSuccess(it: MovieResponse) {
        showProgressBar.set(false)
        adapter.submitList(it.results.toMutableList())
    }

    override fun onCleared() {
        subscription.dispose()
    }
}