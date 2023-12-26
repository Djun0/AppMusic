package com.example.appmusic.comsable


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appmusic.ui.theme.GrayColor
import com.example.appmusic.ui.theme.TextColor

@Composable
fun DividerTextComponent(){
    Row(modifier= Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Divider(modifier = Modifier.fillMaxWidth().weight(1f), color = GrayColor, thickness = 1.dp)
        Text(text="Or", fontSize = 18.sp, color = TextColor, modifier = Modifier.padding(8.dp))
        Divider(modifier = Modifier.fillMaxWidth().weight(1f), color = GrayColor, thickness = 1.dp)
    }
}