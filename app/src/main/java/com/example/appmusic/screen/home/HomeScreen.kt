package com.example.appmusic.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appmusic.FbViewmodel
import com.example.appmusic.comsable.ButtonClick
import com.example.appmusic.screen.auth.signup.SignUpViewModel
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.example.appmusic.navigation.SystemBackButtonHandler

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel = viewModel(), navController: NavController, vm: FbViewmodel){

        Text(text="Setting", modifier = Modifier.fillMaxWidth(), fontSize = 30.sp, textAlign = TextAlign.Center)
        ButtonClick(text = "Log Out",
                onClick = {
                        vm.logout()
                        navController.navigate(Screen.LoginScreen.route)
                          }, isEnabled = true)


}