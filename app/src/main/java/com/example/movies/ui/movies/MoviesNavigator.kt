package com.example.movies.ui.movies

import com.example.movies.api.entities.Movie
import com.example.movies.base.BaseNavigator

interface MoviesNavigator: BaseNavigator {

    fun openDetails(movie: Movie)

}