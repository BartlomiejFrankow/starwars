package com.example.movies.utils


open class SingleEvent<out T>(private val content: T) {

    private var hasBeenHandled = false

    //Allow to get content only one time for single live data
    fun getSingleEvent(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}