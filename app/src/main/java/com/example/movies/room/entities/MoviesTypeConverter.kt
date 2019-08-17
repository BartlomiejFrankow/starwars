package com.example.movies.room.entities

import androidx.room.TypeConverter
import com.example.movies.api.entities.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList

class MoviesTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToMoviesObjList(movies: String?): List<Movie> {
        if (movies == null) return emptyList()

        val listType = object : TypeToken<List<Movie>>() { }.type

        return gson.fromJson(movies, listType)
    }

    @TypeConverter
    fun moviesObjListToString(movies: List<Movie>): String {
        return gson.toJson(movies)
    }

}