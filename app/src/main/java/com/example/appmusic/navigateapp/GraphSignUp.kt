package com.example.appmusic.navigateapp

import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.example.appmusic.screen.login.LoginScreen
import com.example.appmusic.screen.signup.SignUp
import com.example.appmusic.screen.signup.TermAndConditionsScreen

val videoUri: Uri = Uri.parse("android.resource://com.example.appmusic/raw/tunnel")
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
            }
            
        }

    }
}