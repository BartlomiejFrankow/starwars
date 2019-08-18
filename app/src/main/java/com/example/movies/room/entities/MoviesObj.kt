package com.example.movies.room.entities

import androidx.room.*
import com.example.movies.api.entities.Movie
import com.example.movies.api.entities.MovieResponse

@Entity
data class MoviesObj(
    @PrimaryKey(autoGenerate = false) val id: Int = 1, //lock for only one copy in local db
    @ColumnInfo(name = "room_movies_response") val movies: MovieResponse
)

@Dao
interface StarWarsDao {

    //Movies
    @Query("SELECT * FROM moviesobj")
    fun getMovieObj(): MoviesObj?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsetMovieObj(moviesObj: MoviesObj) //insert new obj with id = 1 or delete existing obj and insert new one with id = 1

    @Query("DELETE FROM moviesobj WHERE id = 1")
    fun deleteMovieObj()

    //Characters
    //Vehicles
    //Planets

}