package com.example.movies.room.entities

import androidx.room.*
import com.example.movies.api.entities.Movie

@Entity
data class MoviesListObj(
    @PrimaryKey(autoGenerate = false) val id: Int = 1, //lock for only one copy in local db
    @ColumnInfo(name = "movie_list_count") val count: Int,
    @ColumnInfo(name = "movie_list_next") val next: String?,
    @ColumnInfo(name = "movie_list_previous") val previous: String?,
    @TypeConverters(MoviesTypeConverter::class)
    @ColumnInfo(name = "movie_list_results") val results : List<Movie>
)

@Dao
interface MoviesListObjDao {

    @Query("SELECT * FROM movieslistobj")
    fun getAllMovies(): Array<MoviesListObj>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //insert new obj with id = 1 or delete item and insert new one with id = 1
    fun upsetMovies(moviesListObj: MoviesListObj)

    @Delete
    fun deleteMovies(moviesListObj: MoviesListObj) //TODO need to delete movies at MainActivity onDestroy()

}