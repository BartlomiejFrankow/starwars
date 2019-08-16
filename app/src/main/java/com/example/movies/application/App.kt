package com.example.movies.application

import android.app.Application
import android.content.Context
import com.example.movies.module.appModules
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
    }

    companion object {
        private lateinit var instance: App

        fun appCtx(): Context {
            return instance.applicationContext
        }
    }

}