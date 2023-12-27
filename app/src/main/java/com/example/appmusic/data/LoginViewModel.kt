package com.example.appmusic.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appmusic.data.rules.Validator
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {
    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPass = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)
    fun onEvent(event:LoginUIEvent){
        when(event){
            is LoginUIEvent.EmailChanged->{
                loginUIState.value =loginUIState.value
                    .copy(email = event.email)

            }
            is LoginUIEvent.PasswordChanged->{
                loginUIState.value =loginUIState.value
                    .copy(password = event.password)

            }
            is LoginUIEvent.LoginButtonClicked->{
                login()

            }
        }
        validateLoginUIDataWithRules()
    }

    private fun login() {
        loginInProgress.value=true
        val email =loginUIState.value.email
        val password=loginUIState.value.password
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d(TAG,"Inside login success")
                Log.d(TAG,"I${it.isSuccessful}")

                if(it.isSuccessful){
                    loginInProgress.value=false
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener{
                loginInProgress.value=false
                Log.d(TAG,"Inside login failure")
                Log.d(TAG,"I${it.localizedMessage}")

            }
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