package com.example.appmusic.comsable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonClick(onClick: () -> Unit = { /*TODO*/ }, text:String){
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(text = text, modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun ButtonClickNoborder(onClick: () -> Unit = { /*TODO*/ }, text:String)
{
 TextButton(onClick = { /*TODO*/ }) {
     Text(text = text)
 }
}