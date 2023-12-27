package com.example.appmusic.navigateapp

import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmusic.FbViewmodel
import com.example.appmusic.main.NotificationMessage
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.example.appmusic.screen.home.HomeScreen
import com.example.appmusic.screen.auth.login.LoginScreen
import com.example.appmusic.screen.auth.signup.SignUp
import com.example.appmusic.screen.auth.signup.SuccessScreen
import com.example.appmusic.screen.auth.signup.TermAndConditionsScreen

val videoUri: Uri = Uri.parse("android.resource://com.example.appmusic/raw/tunnel")
/*
@Composable

fun GraphSignUp(){

    Surface(modifier = Modifier.fillMaxSize(), color= Color.White) {
        Crossfade(targetState =AppRouter.currentScreen, label = "") { currentState->
            when(currentState.value){
                is Screen.SignUpScreen ->{
                    SignUp()
                }
                is Screen.TermAndConditionsScreen->{
                    TermAndConditionsScreen()
                }
                is Screen.LoginScreen->{
                    LoginScreen(videoUri = videoUri)
                }
                is Screen.HomeScreen ->{
                    HomeScreen()
                }
            }
            
        }

    }
}*/
@Composable
fun AuthenticationApp(){
    val vm = hiltViewModel<FbViewmodel>()
    val  navController = rememberNavController()
    NotificationMessage(vm)
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(Screen.LoginScreen.route){
            LoginScreen(videoUri = videoUri, vm = vm, navController = navController)
        }
        composable(Screen.TermAndConditionsScreen.route){
            TermAndConditionsScreen()
        }
        composable(Screen.SignUpScreen.route){
            SignUp(navController = navController, vm= vm )
        }
        composable(Screen.HomeScreen.route){
            HomeScreen(vm = vm, navController = navController)
        }
        composable(Screen.SuccessScreen.route){
            SuccessScreen()
        }
    }
}