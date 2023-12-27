package com.example.appmusic.data

sealed class SignupUIEvent{
    data class UserNameChanged(val userName: String) :SignupUIEvent()
    data class EmailChanged( val email: String): SignupUIEvent()
    data class  PasswordChanged( val password: String): SignupUIEvent()
    data class  RepeatPasswordChanged(val repeatPassword: String): SignupUIEvent()
    object RegistrationButtonClicked: SignupUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean):SignupUIEvent()

}
