package com.example.appmusic.screen.auth.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appmusic.data.LoginUIEvent
import com.example.appmusic.data.LoginUIState
import com.example.appmusic.data.rules.Validator
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {
    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPass = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)
    fun onEvent(event: LoginUIEvent){
        when(event){
            is LoginUIEvent.EmailChanged ->{
                loginUIState.value =loginUIState.value
                    .copy(email = event.email)

            }
            is LoginUIEvent.PasswordChanged ->{
                loginUIState.value =loginUIState.value
                    .copy(password = event.password)

            }

        }
        validateLoginUIDataWithRules()
    }


    private fun validateLoginUIDataWithRules(){
        val emailResult = Validator.validatorEmail(email=loginUIState.value.email)
        val passWordResult = Validator.validatorPassword(password = loginUIState.value.password)
        loginUIState.value=loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passWordResult.status
        )
        allValidationPass.value=emailResult.status&&passWordResult.status
    }
}