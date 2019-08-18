package com.example.movies.ui.movies

import android.widget.Toast
import androidx.databinding.ObservableField
import com.example.movies.api.entities.Movie
import com.example.movies.api.entities.MovieResponse
import com.example.movies.api.repositories.MoviesRepository
import com.example.movies.application.App
import com.example.movies.base.BaseViewModel
import com.example.movies.room.entities.MoviesObj
import com.example.movies.room.entities.StarWarsDao
import com.example.movies.utils.EqualsDiffUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MoviesViewModel(val moviesRepository: MoviesRepository, val starWarsDao: StarWarsDao) : BaseViewModel<MoviesNavigator>() {

    private var subscription: Disposable? = null
    var adapter = MovieAdapter(EqualsDiffUtil(), onItemClick = ::onMovieClicked)
    var showProgressBar = ObservableField(true)
    private val moviesFromDb = starWarsDao.getMovieObj()

    private fun onMovieClicked(movie: Movie) {
    }

    fun getMovies() {
        if (moviesFromDb == null) {
            subscription = moviesRepository.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { getMoviesSuccess(it) },
                    { getMoviesError(it) }
                )
        } else adapter.submitList(moviesFromDb.movies.results.toMutableList())
    }

    private fun getMoviesError(it: Throwable) {
        Toast.makeText(App.appCtx(), it.message, Toast.LENGTH_SHORT).show()
        showProgressBar.set(false)
    }

    private fun getMoviesSuccess(it: MovieResponse) {
        adapter.submitList(it.results.toMutableList())
        starWarsDao.upsetMovieObj(MoviesObj(movies = it))
        showProgressBar.set(false)
    }

    override fun onCleared() {
        if (subscription != null) subscription!!.dispose()
    }

}