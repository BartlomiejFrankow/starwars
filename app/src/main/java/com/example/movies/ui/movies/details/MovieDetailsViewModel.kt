package com.example.movies.ui.movies.details

import androidx.databinding.ObservableField
import com.example.movies.api.entities.Movie
import com.example.movies.base.BaseViewModel

class MovieDetailsViewModel: BaseViewModel<MovieDetailsNavigator>() {

    var movie: Movie? = null

    val crawl = ObservableField("")

    fun setDataToView() {
        movie.let {
            crawl.set(it!!.opening_crawl)
        }
    }
}