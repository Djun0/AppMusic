package com.example.appmusic.navigateapp

import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmusic.FbViewmodel
import com.example.appmusic.data.songsList
import com.example.appmusic.main.NotificationMessage
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.example.appmusic.screen.home.HomeScreen
import com.example.appmusic.screen.auth.login.LoginScreen
import com.example.appmusic.screen.auth.signup.SignUp
import com.example.appmusic.screen.auth.signup.SuccessScreen
import com.example.appmusic.screen.auth.signup.TermAndConditionsScreen
import com.example.appmusic.screen.homeplay.HomeScreenPlay

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
@OptIn(ExperimentalMaterial3Api::class)
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
            SignUp(navController = navController, vm= vm ,videoUri = videoUri)
        }
        composable(Screen.HomeScreen.route){
            HomeScreen(vm = vm, navController = navController)
        }
        composable(Screen.SuccessScreen.route){
            SuccessScreen()
        }
        composable(Screen.HomeScreenPlay.route){
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = "Music App Online") },
                    actions = {
                        IconButton(onClick = { navController.navigate(Screen.HomeScreen.route) }) {
                            Icon(imageVector =Icons.Default.Settings, contentDescription =" " )
                        }
                    }

                )
            }
                ) { innerPadding ->
                HomeScreenPlay(songsList = songsList,innerPadding)
            }
        }
    }
}