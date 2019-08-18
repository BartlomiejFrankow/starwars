package com.example.movies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.room.entities.MoviesObj
import com.example.movies.room.entities.MoviesListObjDao

@Database(entities = [MoviesObj::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesListObjDao(): MoviesListObjDao
}