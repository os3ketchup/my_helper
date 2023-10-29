package uz.os3ketchup.myhelper.presentation.viewmodels

sealed class State


object Error : State()
object Progress : State()
object Result : State()
object Initial : State()

