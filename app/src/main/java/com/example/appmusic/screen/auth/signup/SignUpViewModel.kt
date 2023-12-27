package com.example.appmusic.screen.auth.signup

import android.os.Handler
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appmusic.data.RegistrationUiState
import com.example.appmusic.data.SignupUIEvent
import com.example.appmusic.data.rules.Validator
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel:ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName
    var signUpInProgress = mutableStateOf(false)
    var RegistrationUiState = mutableStateOf(RegistrationUiState())
    var allValidationPass = mutableStateOf(false)
    fun onEvent(event: SignupUIEvent){

        when(event){
            is SignupUIEvent.UserNameChanged ->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(userName = event.userName)
                printState()
            }
            is SignupUIEvent.EmailChanged ->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(email = event.email)
                printState()
            }

            is SignupUIEvent.PasswordChanged ->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(password = event.password)
                printState()
            }

            is SignupUIEvent.RepeatPasswordChanged ->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(repeatPassword = event.repeatPassword)
                printState()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked ->{
                RegistrationUiState.value=RegistrationUiState.value.copy(privacyPolicyAccept = event.status)
            }
        }
        validateDataWithRules()
    }


    private fun validateDataWithRules() {
        val privacyPolicyResult=Validator.validatorPrivacyPolicyAcceptance(statusValue =RegistrationUiState.value.privacyPolicyAccept)
        val userNameResult =Validator.validatorUserName(username=RegistrationUiState.value.userName)
        val emailResult = Validator.validatorEmail(email=RegistrationUiState.value.email)
        val passWordResult = Validator.validatorPassword(password = RegistrationUiState.value.password)
        val repeatPassWordResult = Validator.validatorRepeatPassword(repeatPassword = RegistrationUiState.value.repeatPassword)
        Log.d(TAG,"Inside_printState")
        Log.d(TAG,"userNameResult=$userNameResult")
        Log.d(TAG,"emailResult=$emailResult")
        Log.d(TAG,"passWordResult=$passWordResult")
        Log.d(TAG,"repeatPassWordResult=$repeatPassWordResult")
        Log.d(TAG,"privacyPolicyResult=$privacyPolicyResult")
        RegistrationUiState.value=RegistrationUiState.value//cập nhập lại trạng thái đăng ký hiện tại
            .copy(
                userNameError = userNameResult.status,
                emailError =  emailResult.status,
                passwordError = passWordResult.status,
                repeatPasswordError = repeatPassWordResult.status,
                privacyPolicyError = privacyPolicyResult.status
            )
        allValidationPass.value = userNameResult.status&&emailResult.status
                &&passWordResult.status&&repeatPassWordResult.status
                &&privacyPolicyResult.status

    }


    fun printState(){
        Log.d(TAG,"ValidateDataWithRule")
        Log.d(TAG,RegistrationUiState.value.toString())

    }




}