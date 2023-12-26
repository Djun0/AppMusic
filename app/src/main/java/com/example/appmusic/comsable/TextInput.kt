package com.example.appmusic.comsable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon


import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(onValueChange:(String)->Unit, inputType: InputType, focusRequester: FocusRequester?=null, keyboardActions: KeyboardActions){

    val textValue = remember { mutableStateOf("") }
    TextField(value =textValue.value,
        onValueChange = {
            textValue.value=it
            onValueChange(it)
                        },
        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester?: FocusRequester()),//focusRequester để điều khiển focus.
        leadingIcon = {Icon(imageVector = inputType.icon, contentDescription = null)}, //Biểu tượng hiển thị bên trái trường nhập liệu.
        label= { Text(text = inputType.label)},
        shape = RoundedCornerShape(32.dp),//Hình dạng của TextField (được thiết lập là RoundedCornerShape với góc bo tròn 8dp).
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,//Màu của chỉ báo focus.
            unfocusedIndicatorColor = Color.Transparent,//Màu của chỉ báo khi không focus.
            disabledIndicatorColor =  Color.Transparent//Màu của chỉ báo khi TextField bị vô hiệu hóa.
        ),
        singleLine = true,// Giới hạn nhập liệu chỉ một dòng.
        keyboardOptions = inputType.keyboardOptions,//  Các tùy chọn bàn phím, lấy từ đối tượng InputType
        visualTransformation = inputType.visualTransformation,//Chuyển đổi hiển thị, lấy từ đối tượng InputType
        keyboardActions = keyboardActions //Xử lý các hành động của bàn phím.
        )
}
//Cấu hình trường nhập liệu
sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
){
    object LoginName: InputType(
        label = "Username",
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),//khai báo kiểu bàn phím
        visualTransformation = VisualTransformation.None //visualTransformation: Chuyển đổi hiển thị, ví dụ như ẩn/hiện mật khẩu.
    )                                                    // ở đây tùy chọn: không có
    object Email: InputType(
        label = "Email",
        icon = Icons.Default.Email,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),//khai báo kiểu bàn phím
        visualTransformation = VisualTransformation.None //visualTransformation: Chuyển đổi hiển thị, ví dụ như ẩn/hiện mật khẩu.
    )
    object LoginPassWord:InputType(
        label = "Password",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()//PasswordVisualTransformation():
    )                                                   // Đối tượng này dùng để ẩn/hiện mật khẩu khi nhập liệu, bảo vệ thông tin nhạy cảm.
    object CreatePassWord:InputType(
        label = "Password",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
    object RepeatPassWord:InputType(
        label = "Repeat Password",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
    object CreateName: InputType(
        label = "Username",
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),//khai báo kiểu bàn phím
        visualTransformation = VisualTransformation.None //visualTransformation: Chuyển đổi hiển thị, ví dụ như ẩn/hiện mật khẩu.
    )

}