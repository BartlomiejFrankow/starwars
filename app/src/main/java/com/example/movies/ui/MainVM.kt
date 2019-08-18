package com.example.movies.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.movies.base.BaseViewModel
import com.example.movies.room.entities.StarWarsDao
import com.example.movies.utils.SingleEvent

class MainVM(val starWarsDao: StarWarsDao) : BaseViewModel<MainNavigator>() {

    var isBottomNavigationVisible = ObservableField(true)

    val actionRight = MutableLiveData<SingleEvent<Unit>>()

    fun onToolbarRightIconClicked() {
        //Need to observe at particular fragment
        actionRight.postValue(SingleEvent(Unit))
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        starWarsDao.deleteMovieObj()
    }

}