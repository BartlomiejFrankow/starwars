package com.example.movies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.room.entities.MoviesObj
import com.example.movies.room.entities.StarWarsDao
import com.example.movies.room.entities.MoviesTypeConverter

@Database(entities = [MoviesObj::class], version = 1, exportSchema = true)
@TypeConverters(MoviesTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesListObjDao(): StarWarsDao

}