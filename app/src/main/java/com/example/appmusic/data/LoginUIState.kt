package com.example.appmusic.data

data class LoginUIState (

    var email :String = "",
    var password :String = "",

//biến kiểm tra trạng thái hợp lệ của dữ liệu người dùng nhậpvao
    var emailError :Boolean = false,
    var passwordError :Boolean = false,

    var loginFail:Boolean= true
)