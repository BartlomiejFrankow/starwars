package com.example.movies.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.movies.module.appModules
import com.example.movies.room.AppDatabase
import com.example.movies.room.entities.StarWarsObjDao
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    init {
        instance = this
    }


    override fun onCreate() {
        super.onCreate()
        // Adding Koin modules to our application
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }

        database = Room.databaseBuilder(this, AppDatabase::class.java, "db-StarWars").allowMainThreadQueries().build()

    }

    companion object {
        private lateinit var instance: App
        private lateinit var database: AppDatabase

        fun appCtx(): Context {
            return instance.applicationContext
        }

        fun db(): AppDatabase {
            return database
        }

        fun getMoviesListObjDao(): StarWarsObjDao = database.moviesListObjDao()

    }

}