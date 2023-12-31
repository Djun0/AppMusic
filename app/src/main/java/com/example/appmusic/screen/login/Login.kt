package com.example.appmusic.screen.login

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appmusic.R
import com.example.appmusic.comsable.ButtonClick
import com.example.appmusic.comsable.ButtonClickNoborder
import com.example.appmusic.comsable.ClickableLoginComponent
import com.example.appmusic.comsable.DividerTextComponent

import com.example.appmusic.comsable.InputType
import com.example.appmusic.comsable.TextInput
import com.example.appmusic.comsable.VideoPlay
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen


@Composable
fun LoginScreen(videoUri: Uri){
    //val context = LocalContext.current   dùng Exo
    //val exoPlayer = remember { context.buildExoPlayer(videoUri) }
    val passwordFocusRequest:FocusRequester=FocusRequester()
    //passwordFocusRequest dùng để điều khiển focus đến trường nhập mật khẩu sau khi nhập xong tên
    val focusManager = LocalFocusManager.current


//ở đây dùng Exo
    /*DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }*/

    Box(Modifier.imePadding()){
        VideoPlay(videoUri = videoUri, modifier = Modifier.fillMaxHeight())
        // imePadding() có tác dụng thêm padding cho các thành phần giao diện khi bàn phím xuất hiện.
        Column( modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),//căn các thành phần con xuống dưới cùng
            horizontalAlignment = Alignment.CenterHorizontally //căn các thành phần con chính giữa theo chiều ngang

        ) {
            Icon(painter = painterResource(id = R.drawable.app_logo), contentDescription = null,tint = Color.White,modifier=Modifier.size(80.dp))
            TextInput(
                onValueChange ={},
                inputType = InputType.Email,
                keyboardActions = KeyboardActions(onNext = {passwordFocusRequest.requestFocus()} )
            )
            TextInput(

                onValueChange ={},
                inputType = InputType.LoginPassWord,
                keyboardActions = KeyboardActions(onDone={focusManager.clearFocus()}),
                focusRequester = passwordFocusRequest
            )
            ButtonClick(text = "Login")
            DividerTextComponent()

            ClickableLoginComponent(tryingToLogin = false,text="Register",onTextSelected={
                AppRouter.navigateTo(Screen.SignUpScreen)

            })
            Spacer(modifier = Modifier.padding(20.dp))
        }

    }
}
//ở đây dùng Exo
/*
private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }
private fun Context.doLogin() {
    Toast.makeText(
        this,
        "Something went wrong, try again later!",
        Toast.LENGTH_SHORT
    ).show()
}
private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        useController = false
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    }
*/