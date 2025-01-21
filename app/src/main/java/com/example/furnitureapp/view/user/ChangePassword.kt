package com.example.furnitureapp.view.user

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furnitureapp.R
import com.example.furnitureapp.components.SpacerHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.furnitureapp.components.BackButton
import com.example.furnitureapp.viewmodel.AuthState
import com.example.furnitureapp.viewmodel.UserViewModel
import isValidPassword

@Composable
fun ChangePasswordScreen(userId:Int,navController: NavController,userViewModel: UserViewModel){
    val userState by userViewModel::userState

    val user = (userState as AuthState.XacThuc).user

    var oldPass by remember { mutableStateOf("") }
    var newPass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    val oldPassFocusRequester = remember { FocusRequester() }
    val newPassFocusRequester = remember { FocusRequester() }
    val confirmPassFocusRequester = remember { FocusRequester() }

    val userOldPass = user.mat_khau

    val context = LocalContext.current
    BackButton(onClick = {navController.popBackStack()})
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Đổi mật khẩu ",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
        )
        SpacerHeight(32.dp)
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                .focusRequester(oldPassFocusRequester),
            value = oldPass,
            onValueChange = { oldPass = it },
            label = { Text(text = "Mật khẩu cũ") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFF9e9e9e)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF896de7),
                unfocusedContainerColor = Color(0xFFf4f4f4),
                focusedContainerColor = Color(0xFFF3F0FD),

                ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            shape = RoundedCornerShape(14.dp),
        )
        SpacerHeight(18.dp)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                .focusRequester(newPassFocusRequester),
            value = newPass,
            onValueChange = { newPass = it },
            label = { Text(text = "Mật khẩu mới") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFF9e9e9e)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF896de7),
                unfocusedContainerColor = Color(0xFFf4f4f4),
                focusedContainerColor = Color(0xFFF3F0FD),

                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            shape = RoundedCornerShape(14.dp),
        )
        SpacerHeight(18.dp)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                .focusRequester(confirmPassFocusRequester),
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = { Text(text = "Xác nhận mật khẩu") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.padlock),
                    contentDescription = "Lock Icon",
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFF9e9e9e)
                )
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF896de7),
                unfocusedContainerColor = Color(0xFFf4f4f4),
                focusedContainerColor = Color(0xFFF3F0FD),

                ),
            shape = RoundedCornerShape(14.dp),
        )
        SpacerHeight(26.dp)
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(54.dp),
            onClick = {
                when {
                    oldPass.isEmpty() -> {
                        oldPassFocusRequester.requestFocus()
                        Toast.makeText(context, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
                    }

                    newPass.isEmpty() -> {
                        newPassFocusRequester.requestFocus()
                        Toast.makeText(context, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT)
                            .show()
                    }
                    !isValidPassword(newPass) -> {
                        newPassFocusRequester.requestFocus()
                        Toast.makeText(context, "Mật khẩu phải ít nhất 8 ký tự và không chứa khoảng trắng", Toast.LENGTH_SHORT).show()
                    }

                    confirmPass.isEmpty() -> {
                        confirmPassFocusRequester.requestFocus()
                        Toast.makeText(context, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show()
                    }
                    oldPass != userOldPass -> {
                        oldPassFocusRequester.requestFocus()
                        Toast.makeText(context, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show()
                    }
                    newPass != confirmPass -> {
                        confirmPassFocusRequester.requestFocus()
                        Toast.makeText(context, "Mật khẩu mới và xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                    }
                    else -> {

                        userViewModel.userChangePassword(userId,newPass.trim()){res->
                            if(res.error){
                                Toast.makeText(context, res.message, Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(context, res.message, Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            }

                        }

                    }
                }
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF896de7),
                contentColor = Color.White
            )
        ) {
            Text(text = "Xác nhận", fontWeight = FontWeight.W400, fontSize = 18.sp)
        }
    }
}