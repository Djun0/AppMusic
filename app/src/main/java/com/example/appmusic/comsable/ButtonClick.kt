package com.example.appmusic.comsable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appmusic.ui.theme.Primary
import com.example.appmusic.ui.theme.Secondary

@Composable
fun ButtonClick(onClick: () -> Unit = { /*TODO*/ }, text:String, isEnabled:Boolean= false){
    Button(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors= ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary,Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
            ){
            Text(text = text, modifier = Modifier.padding(vertical = 8.dp))
        }

    }
}
@Composable
fun ButtonClickNoborder(onClick: () -> Unit = { /*TODO*/ }, text:String)
{
    TextButton(onClick = { /*TODO*/ }) {
        Text(text = text)
    }
}

@Composable
fun ClickableComponent(text: String,onTextSelected:(String)->Unit)
{
    val initialText= "By continuing you are indicating that you accept our "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termsAndConditionsText ="Term of Use"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(SpanStyle(Color.Cyan)){
            pushStringAnnotation(tag=privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(SpanStyle(Color.Cyan)){
            pushStringAnnotation(tag=termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }
 ClickableText(text = annotatedString,
     onClick = {offset->annotatedString
     .getStringAnnotations(offset,offset)
     .firstOrNull()?.also { span-> Log.d("ClickableComponent","{$span}")
             if(span.item==termsAndConditionsText|| (span.item==privacyPolicyText)){
                 onTextSelected(span.item)
             }
     }
 } )
}
@Composable
fun CheckBoxComponent(text: String,onTextSelected:(String)->Unit){
    Row(modifier= Modifier
        .heightIn(56.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        val checkedState = remember { mutableStateOf(false) }
        Checkbox(checked =checkedState.value , onCheckedChange = {checkedState!=checkedState})
        ClickableComponent(text=text,onTextSelected)
    }
}
@Composable
fun ClickableLoginComponent(text: String,onTextSelected:(String)->Unit,tryingToLogin: Boolean =true)
{
    val initialText= if(tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if(tryingToLogin) "Login" else "Register"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(SpanStyle(Color.Cyan)){
            pushStringAnnotation(tag=loginText, annotation = loginText)
            append(loginText)
        }

    }
    ClickableText(text = annotatedString,
        style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal, textAlign = TextAlign.Center)
        ,
        onClick = {offset->annotatedString
            .getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span-> Log.d("ClickableComponent","{$span}")
                if(span.item==loginText){
                    onTextSelected(span.item)
                }
            }
        } )
}