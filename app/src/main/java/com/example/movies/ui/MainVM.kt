package com.example.movies.ui

import androidx.lifecycle.LifecycleOwner
import com.example.movies.base.BaseViewModel
import com.example.movies.room.entities.StarWarsObjDao

class MainVM(val starWarsObjDao: StarWarsObjDao) : BaseViewModel<MainNavigator>() {

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        starWarsObjDao.deleteMovieFromDb()
    }

}