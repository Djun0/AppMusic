package com.example.appmusic.screen

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.appmusic.R
import com.example.appmusic.comsable.ButtonClick
import com.example.appmusic.comsable.ButtonClickNoborder
import com.example.appmusic.comsable.InputType
import com.example.appmusic.comsable.TextInput
import com.example.appmusic.comsable.VideoPlay
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun Login(videoUri: Uri){
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
                value = "", onValueChange ={},
                inputType = InputType.Name,
                keyboardActions = KeyboardActions(onNext = {passwordFocusRequest.requestFocus()} )
            )
            TextInput(
                value = "",
                onValueChange ={},
                inputType = InputType.PassWord,
                keyboardActions = KeyboardActions(onDone={focusManager.clearFocus()}),
                focusRequester = passwordFocusRequest
            )
            ButtonClick(text = "SIGN UP")
            Divider(color = Color.White.copy(alpha = 0.3f), thickness = 1.dp, modifier = Modifier.padding(48.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text="Bạn không có tài khoản?", color = Color.White)
                ButtonClickNoborder(text = "Đăng ký")
            }
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