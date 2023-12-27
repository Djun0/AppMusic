package com.example.appmusic.data

data class RegistrationUiState(
    var userName :String = "",
    var email :String = "",
    var password :String = "",
    var repeatPassword: String = "",
    var privacyPolicyAccept:Boolean=false,

//biến kiểm tra trạng thái hợp lệ của dữ liệu người dùng nhậpvao
    var userNameError :Boolean = false,
    var emailError :Boolean = false,
    var passwordError :Boolean = false,
    var repeatPasswordError :Boolean = false,
//biến kiểm tra người dùng đã chấp thuận điều khoản
    var privacyPolicyError: Boolean = false
    )