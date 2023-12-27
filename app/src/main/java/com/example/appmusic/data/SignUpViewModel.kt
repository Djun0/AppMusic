package com.example.appmusic.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appmusic.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel:ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName
    var RegistrationUiState = mutableStateOf(RegistrationUiState())
    var allValidationPass = mutableStateOf(false)
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
            is UiEvent.RegistrationButtonClicked ->{signUp()
            }
            is UiEvent.PrivacyPolicyCheckBoxClicked->{
                RegistrationUiState.value=RegistrationUiState.value.copy(privacyPolicyAccept = event.status)
            }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG,"Inside_signUp")
        printState()
        createUserInFirebase(email = RegistrationUiState.value.email, password = RegistrationUiState.value.password)
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
    private fun createUserInFirebase(email:String, password:String){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d(TAG,"Inside_OnCompleteListener")
                Log.d(TAG,"isSuccessful=${it.isSuccessful}")

            }
            .addOnFailureListener{
                Log.d(TAG,"Inside_OnFailureListener")
                Log.d(TAG,"Exception=${it.message}")
                Log.d(TAG,"Exception=${it.localizedMessage}")

            }
    }


}