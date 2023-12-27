package com.example.appmusic.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.appmusic.FbViewmodel

@Composable
fun NotificationMessage(vm:FbViewmodel){
    val notifState= vm.popupNotification.value
    val notifMessage =notifState?.getContentOrNull()
    if(notifMessage!= null){
        Toast.makeText(LocalContext.current,notifMessage,Toast.LENGTH_SHORT).show()
    }
}