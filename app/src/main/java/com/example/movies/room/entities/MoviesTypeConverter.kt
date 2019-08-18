package com.example.movies.room.entities

import androidx.room.TypeConverter
import com.example.movies.api.entities.Movie
import com.example.movies.api.entities.MovieResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList

class MoviesTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToMoviesObjList(movies: String?): MovieResponse? {
        if (movies == null) return null

        val listType = object : TypeToken<MovieResponse>() { }.type

        return gson.fromJson(movies, listType)
    }

    @TypeConverter
    fun moviesObjListToString(movies: MovieResponse): String {
        return gson.toJson(movies)
    }

}