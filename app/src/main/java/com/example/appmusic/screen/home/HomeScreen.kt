package com.example.appmusic.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appmusic.comsable.ButtonClick
import com.example.appmusic.data.SignUpViewModel
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.example.appmusic.navigation.SystemBackButtonHandler

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = viewModel()){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {
        Text(text="Setting", modifier = Modifier.fillMaxWidth(), fontSize = 30.sp, textAlign = TextAlign.Center)
        ButtonClick(text = "Log Out", onClick = {signUpViewModel.logout()}, isEnabled = true)
    }





    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}