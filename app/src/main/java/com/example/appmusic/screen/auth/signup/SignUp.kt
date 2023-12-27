package com.example.appmusic.screen.auth.signup

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appmusic.FbViewmodel
import com.example.appmusic.R
import com.example.appmusic.comsable.ButtonClick

import com.example.appmusic.comsable.CheckBoxComponent
import com.example.appmusic.comsable.ClickableLoginComponent
import com.example.appmusic.comsable.DividerTextComponent
import com.example.appmusic.comsable.InputType
import com.example.appmusic.comsable.TextInput
import com.example.appmusic.comsable.VideoPlay
import com.example.appmusic.data.SignupUIEvent
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.example.appmusic.navigation.SystemBackButtonHandler


@Composable
fun SignUp(videoUri: Uri, signUpViewModel: SignUpViewModel = viewModel(), navController:NavController, vm: FbViewmodel){
    val passwordFocusRequest:FocusRequester=FocusRequester()
    val passwordRepeatFocusRequest:FocusRequester=FocusRequester()
    val emailFocusRequest:FocusRequester=FocusRequester()
    //passwordFocusRequest dùng để điều khiển focus đến trường nhập mật khẩu sau khi nhập xong tên
    val focusManager = LocalFocusManager.current
    Box(Modifier.imePadding(), contentAlignment = Alignment.Center) {
        VideoPlay(videoUri = videoUri, modifier = Modifier.fillMaxHeight())
        // imePadding() có tác dụng thêm padding cho các thành phần giao diện khi bàn phím xuất hiện.
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            ),//căn các thành phần con xuống dưới cùng
            horizontalAlignment = Alignment.CenterHorizontally //căn các thành phần con chính giữa theo chiều ngang

        ) {
            Icon(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(80.dp)
            )
            TextInput(
                errorStatus = signUpViewModel.RegistrationUiState.value.userNameError ,
                onValueChange = {signUpViewModel.onEvent(SignupUIEvent.UserNameChanged(it))},
                inputType = InputType.CreateName,
                keyboardActions = KeyboardActions(onNext = {emailFocusRequest.requestFocus() })
            )
            TextInput(
                errorStatus = signUpViewModel.RegistrationUiState.value.emailError ,
                onValueChange = {signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))},
                inputType = InputType.Email,
                keyboardActions = KeyboardActions(onNext = { passwordFocusRequest.requestFocus() }),
                focusRequester = emailFocusRequest
            )
            TextInput(
                errorStatus = signUpViewModel.RegistrationUiState.value.passwordError ,
                onValueChange = { signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))},
                inputType = InputType.CreatePassWord,
                keyboardActions = KeyboardActions(onNext = { passwordRepeatFocusRequest.requestFocus() }),
                focusRequester = passwordFocusRequest
            )
            TextInput(
                errorStatus = signUpViewModel.RegistrationUiState.value.repeatPasswordError ,
                onValueChange = {signUpViewModel.onEvent(SignupUIEvent.RepeatPasswordChanged(it))},
                inputType = InputType.RepeatPassWord,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                focusRequester = passwordRepeatFocusRequest
            )
            Spacer(modifier = Modifier.padding(20.dp))
            CheckBoxComponent(
                text="By continuing you are indicating that you accept",
                onTextSelected={
                    navController.navigate(Screen.TermAndConditionsScreen.route) },
                onCheckChange = {
                    signUpViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
                )
            ButtonClick(text = "Register", onClick = {
                                                        vm.onSignup(signUpViewModel.RegistrationUiState.value.email,signUpViewModel.RegistrationUiState.value.password)
                                                        if(vm.flagSuccess.value) navController.navigate(Screen.LoginScreen.route)
                                                     },
                isEnabled = signUpViewModel.allValidationPass.value)
            DividerTextComponent()
            ClickableLoginComponent(tryingToLogin = true,text="Login",onTextSelected={
                navController.navigate(Screen.LoginScreen.route)
            })

        }

        if(vm.inProgress.value){
            CircularProgressIndicator()
        }


    }}
