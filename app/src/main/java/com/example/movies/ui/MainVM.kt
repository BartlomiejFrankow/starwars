package com.example.movies.ui

import androidx.lifecycle.LifecycleOwner
import com.example.movies.base.BaseViewModel
import com.example.movies.room.entities.StarWarsDao

class MainVM(val starWarsDao: StarWarsDao) : BaseViewModel<MainNavigator>() {

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        starWarsDao.deleteMovieObj()
    }

}