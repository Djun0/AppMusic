package com.example.appmusic.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(val route:String){
    object SignUpScreen:Screen("Signup")
    object TermAndConditionsScreen:Screen("Term")
    object LoginScreen:Screen("Login")
    object HomeScreen:Screen("Home")
    object SuccessScreen:Screen("success")
}
object AppRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)
    fun navigateTo(destination:Screen){
        currentScreen.value=destination
    }
}