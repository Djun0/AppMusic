package com.example.appmusic

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.appmusic.navigateapp.AuthenticationApp


import com.example.appmusic.screen.auth.signup.SignUp
import com.example.appmusic.ui.theme.AppMusicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor=getColor(R.color.white)
            window.navigationBarColor=getColor(R.color.white)
            AppMusicTheme {
                // A surface container using the 'background' color from the theme

                    AuthenticationApp()
                    //GraphSignUp()
                        //Login(videoUri=videoUri)

            }
        }
    }
    @SuppressLint("DiscouragedApi")
    private fun getVideoUri(): Uri {
        val rawId = resources.getIdentifier("clouds", "raw", packageName)
        val videoUri = "android.resource://$packageName/$rawId"
        return Uri.parse(videoUri)
    }
}


