package com.example.appmusic.comsable

import android.net.Uri
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoPlay(
    videoUri:Uri,modifier: Modifier=Modifier
){
    AndroidView(modifier=modifier,
        factory ={context->VideoView(context)
                        .apply{
                        setVideoURI(videoUri)
                        setOnPreparedListener{ start()
                            it.isLooping = true}
                        }
        }
                )
}