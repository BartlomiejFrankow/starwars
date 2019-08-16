package com.example.movies.application

open class Actions

//Base
object Finish : Actions()

object HideKeyboard : Actions()
object BackPress : Actions()
object DismissAction : Actions()
object Logout : Actions()
data class Message(val content: String = "") : Actions()
data class Loading(val content: Boolean = false) : Actions()


object Transition: Actions()
data class Navigate(val id :Int): Actions()