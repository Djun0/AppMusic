package com.example.appmusic.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object SignUpScreen:Screen()
    object TermAndConditionsScreen:Screen()
    object LoginScreen:Screen()
}
object AppRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)
    fun navigateTo(destination:Screen){
        currentScreen.value=destination
    }
}