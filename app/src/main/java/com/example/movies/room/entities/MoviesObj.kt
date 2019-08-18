package com.example.movies.room.entities

import androidx.room.*
import com.example.movies.api.entities.Movie
import com.example.movies.api.entities.MovieResponse

@Entity
data class MoviesObj(
    @PrimaryKey(autoGenerate = false) val id: Int = 1, //lock for only one copy in local db
    @TypeConverters(MoviesTypeConverter::class)
    @ColumnInfo(name = "room_movies_response") val movies: MovieResponse
)

@Dao
interface MoviesListObjDao {

    @Query("SELECT * FROM moviesobj")
    fun getAllMovies(): Array<MoviesObj>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //insert new obj with id = 1 or delete item and insert new one with id = 1
    fun upsetMovies(moviesObj: MoviesObj)

    @Delete
    fun deleteMovies(moviesObj: MoviesObj) //TODO need to delete movies at MainActivity onDestroy()

}