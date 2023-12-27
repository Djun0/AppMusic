package com.example.appmusic.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
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
            is SignupUIEvent.UserNameChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(userName = event.userName)
                printState()
            }

            is SignupUIEvent.EmailChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(email = event.email)
                printState()
            }

            is SignupUIEvent.PasswordChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(password = event.password)
                printState()
            }

            is SignupUIEvent.RepeatPasswordChanged->{ RegistrationUiState.value =RegistrationUiState.value
                .copy(repeatPassword = event.repeatPassword)
                printState()
            }
            is SignupUIEvent.RegistrationButtonClicked ->{signUp()
            }
            is SignupUIEvent.PrivacyPolicyCheckBoxClicked->{
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
    //hàm để tạo tài khoản
    private fun createUserInFirebase(email:String, password:String){
        signUpInProgress.value= true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d(TAG,"Inside_OnCompleteListener")
                Log.d(TAG,"isSuccessful=${it.isSuccessful}")
                signUpInProgress.value= false
                if(it.isSuccessful){//khi tạo tk thành công tiến hành điều hướng
                    AppRouter.navigateTo(Screen.HomeScreen)
                }

            }
            .addOnFailureListener{
                Log.d(TAG,"Inside_OnFailureListener")
                Log.d(TAG,"Exception=${it.message}")
                Log.d(TAG,"Exception=${it.localizedMessage}")

            }
    }
    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()//Lấy instance của FirebaseAuth để tương tác với hệ thống xác thực của Firebase.

        firebaseAuth.signOut()// Gọi phương thức signOut() để đăng xuất người dùng hiện tại khỏi Firebase.

        val authStateListener = FirebaseAuth.AuthStateListener {
            //Kiểm tra xem currentUser có null hay không để xác định xem người dùng đã đăng xuất thành công chưa.
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
                AppRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }
        //Tạo đối tượng AuthStateListener để lắng nghe sự thay đổi trạng thái xác thực của người dùng.


        firebaseAuth.addAuthStateListener(authStateListener)
    }


}