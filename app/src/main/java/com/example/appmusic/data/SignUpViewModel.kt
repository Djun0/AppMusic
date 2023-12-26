package com.example.appmusic.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel:ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName
    var RegistrationUiState = mutableStateOf(RegistrationUiState())
    fun onEvent(event: UiEvent){
        when(event){
            is UiEvent.UserNameChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(userName = event.userName)
                printState()
            }

            is UiEvent.EmailChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(email = event.email)
                printState()
            }

            is UiEvent.PasswordChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(password = event.password)
                printState()
            }

            is UiEvent.RepeatPasswordChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(repeatPassword = event.repeatPassword)
                printState()
            }
        }
    }

    fun printState(){
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,RegistrationUiState.value.toString())

    }


}