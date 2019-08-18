package com.example.movies.ui.movies.details

import com.example.movies.api.entities.Movie
import com.example.movies.base.BaseViewModel

class MovieDetailsViewModel: BaseViewModel<MovieDetailsNavigator>() {

    var movie: Movie? = null

}