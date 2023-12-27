package com.example.appmusic.data

sealed class UiEvent{
    data class UserNameChanged(val userName: String) :UiEvent()
    data class EmailChanged( val email: String): UiEvent()
    data class  PasswordChanged( val password: String): UiEvent()
    data class  RepeatPasswordChanged(val repeatPassword: String): UiEvent()
    object RegistrationButtonClicked: UiEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean):UiEvent()

}
