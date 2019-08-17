package com.example.movies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.room.entities.MoviesListObj
import com.example.movies.room.entities.MoviesListObjDao

@Database(entities = [MoviesListObj::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesListObjDao(): MoviesListObjDao
}