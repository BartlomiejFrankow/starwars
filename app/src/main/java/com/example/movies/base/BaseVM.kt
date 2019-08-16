package com.example.movies.base
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel

abstract class BaseVM<N : Any>: ViewModel(), DefaultLifecycleObserver {

    private lateinit var mNavigator: N
    fun getNav(): N = mNavigator
    fun setNav(navigator: N) {
        this.mNavigator = navigator
    }

//    var parentActivityViewModel: BaseVM? = null
//
//    fun setSubscriber(parentActivityViewModel: BaseVM) {
//        this.parentActivityViewModel = parentActivityViewModel
//    }

}