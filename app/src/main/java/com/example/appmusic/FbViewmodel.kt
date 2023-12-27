package com.example.appmusic

import android.os.Handler
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.appmusic.data.LoginUIState
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FbViewmodel @Inject constructor(val auth: FirebaseAuth):ViewModel(){
    var _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState: StateFlow<LoginUIState> = _loginUIState.asStateFlow()


    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val popupNotification = mutableStateOf<Event<String>?>(null)
    val flagSuccess =mutableStateOf(false)
    fun onSignup(email:String, password:String){
        flagSuccess.value=true
        inProgress.value=true
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    flagSuccess.value=true
                    signedIn.value=true
                    handleException(it.exception,"Signup successful")
                } else{
                    flagSuccess.value=false
                    handleException(it.exception,"Signup fail")

                }
                inProgress.value=false
            }
    }

    fun logout() {
        inProgress.value=true
        val firebaseAuth = FirebaseAuth.getInstance()//Lấy instance của FirebaseAuth để tương tác với hệ thống xác thực của Firebase.
        firebaseAuth.signOut()// Gọi phương thức signOut() để đăng xuất người dùng hiện tại khỏi Firebase.
        val authStateListener = FirebaseAuth.AuthStateListener {
            //Kiểm tra xem currentUser có null hay không để xác định xem người dùng đã đăng xuất thành công chưa.
            if (it.currentUser == null) {

                    AppRouter.navigateTo(Screen.LoginScreen) // Điều hướng đến màn hình đăng nhập

            }
            inProgress.value=false
        }
        //Tạo đối tượng AuthStateListener để lắng nghe sự thay đổi trạng thái xác thực của người dùng.
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun login(email: String,password: String){

        inProgress.value=true
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){

                    signedIn.value=true
                    _loginUIState.value=_loginUIState.value.copy(loginFail = false)
                    handleException(it.exception,"Login successful")
                }else{
                    _loginUIState.value=_loginUIState.value.copy(loginFail = true)
                    handleException(it.exception,"Login fail")
                }
                inProgress.value=false
            }
    }
    fun handleException(exception: Exception?=null,customMessage:String=""){
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?:""
        val message= if(customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value=Event(message)
    }


}