package com.example.movies.home

import android.util.Log
import android.widget.Toast
import com.example.movies.api.Movie
import com.example.movies.api.MoviesRepository
import com.example.movies.application.App
import com.example.movies.base.BaseVM
import com.example.movies.utils.EqualsDiffUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class HomeVM(val moviesRepository: MoviesRepository) : BaseVM<HomeNavigator>() {

    private lateinit var subscription: Disposable

    var adapter = MovieAdapter(EqualsDiffUtil(), onItemClick = ::onMovieClicked)

    private fun onMovieClicked(movie: Movie) {
    }

    fun getMovies() {
        subscription = moviesRepository.getMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { adapter.submitList(it.data.toMutableList()) },
                { Toast.makeText(App.appCtx(), it.message, Toast.LENGTH_SHORT).show() }
            )
    }

    override fun onCleared() {
        subscription.dispose()
    }
}