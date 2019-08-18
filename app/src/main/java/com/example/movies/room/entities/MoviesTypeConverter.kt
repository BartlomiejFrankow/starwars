package com.example.movies.room.entities

import androidx.room.TypeConverter
import com.example.movies.api.entities.MovieResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MoviesTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToMoviesObjList(movies: String?): MovieResponse? {
        if (movies == null) return null
        val objectType = object : TypeToken<MovieResponse>() { }.type

        return gson.fromJson(movies, objectType)
    }

    @TypeConverter
    fun moviesObjListToString(movies: MovieResponse): String {
        return gson.toJson(movies)
    }

}