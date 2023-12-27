package com.example.appmusic.screen.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appmusic.navigation.AppRouter
import com.example.appmusic.navigation.Screen
import com.example.appmusic.navigation.SystemBackButtonHandler

@Composable
fun TermAndConditionsScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {
        Text(text="Term of Use", modifier = Modifier.fillMaxWidth(), fontSize = 30.sp, textAlign = TextAlign.Center)
    }
}
@Preview
@Composable
fun PreviewTermAndConditionsScreen(){
    TermAndConditionsScreen()
}