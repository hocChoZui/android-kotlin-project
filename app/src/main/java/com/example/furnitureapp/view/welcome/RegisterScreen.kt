package com.example.furnitureapp.view.welcome

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import capitalizeWords
import com.example.furnitureapp.R
import com.example.furnitureapp.components.BackButton
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.components.SpacerWidth

import com.example.furnitureapp.viewmodel.UserViewModel
import isValidPassword
import isValidUsername


@Composable
fun RegisterScreen(modifier: Modifier = Modifier, navController: NavController,userViewModel: UserViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }

    val fullnameFocusRequester = remember { FocusRequester() }
    val usernameFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val context = LocalContext.current

Column (modifier = Modifier.fillMaxSize()){
    SpacerHeight(28.dp)
    BackButton{navController.popBackStack()}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.fur_logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp),
            contentScale = ContentScale.Fit,

            )
        SpacerHeight(28.dp)
        Text(
            text = "Đăng ký ",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
        )
        SpacerHeight(32.dp)
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).focusRequester(fullnameFocusRequester),
            value = fullname,
            onValueChange = { fullname   = it },
            label = { Text(text = "Họ tên") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFF9e9e9e))
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF896de7),
                unfocusedContainerColor = Color(0xFFf4f4f4),
                focusedContainerColor = Color(0xFFF3F0FD),

                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp) .focusRequester(usernameFocusRequester),
            value = username,
            onValueChange = { username   = it },
            label = { Text(text = "Tên đăng nhập") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFF9e9e9e))
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF896de7),
                unfocusedContainerColor = Color(0xFFf4f4f4),
                focusedContainerColor = Color(0xFFF3F0FD),

                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp) .focusRequester(passwordFocusRequester),
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Mật khẩu") },
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
                    fullname.isEmpty() -> {
                        fullnameFocusRequester.requestFocus()
                        Toast.makeText(context, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
                    }

                    username.isEmpty() -> {
                        usernameFocusRequester.requestFocus()
                        Toast.makeText(context, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT)
                            .show()
                    }
                    !isValidUsername(username) -> {
                        usernameFocusRequester.requestFocus()
                        Toast.makeText(context, "Tên đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show()
                    }
                    password.isEmpty() -> {
                        passwordFocusRequester.requestFocus()
                        Toast.makeText(context, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show()
                    }
                    !isValidPassword(password) -> {
                        passwordFocusRequester.requestFocus()
                        Toast.makeText(context, "Mật khẩu phải ít nhất 8 ký tự và không chứa khoảng trắng", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        val formattedFullname = capitalizeWords(fullname.trim())
                        userViewModel.userSignUp(formattedFullname,username.trim(),password.trim()){res->
                            if(res.error){
                                Toast.makeText(context, res.message, Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(context, res.message, Toast.LENGTH_SHORT).show()
                                navController.navigate("login_screen")
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
            Text(text = "Đăng ký", fontWeight = FontWeight.W400, fontSize = 18.sp)
        }


        SpacerHeight(26.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.LightGray,
                thickness = 0.8.dp
            )

            Text(
                text = "hoặc",
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.titleSmall
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.LightGray,
                thickness = 0.8.dp
            )
        }

        SpacerHeight(18.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,

            ){
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .width(90.dp)
                    .height(55.dp),
                border = BorderStroke(0.7.dp, color = Color(0xFF9e9e9e)),
                shape = RoundedCornerShape(24)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "facebook icon",
                    modifier = Modifier.size(32.dp),
                    tint = (Color.Unspecified)
                )
            }
            SpacerWidth(24.dp)
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .width(90.dp)
                    .height(55.dp),
                border = BorderStroke(0.7.dp, color = Color(0xFF9e9e9e)),
                shape = RoundedCornerShape(24)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google icon",
                    modifier = Modifier.size(32.dp),
                    tint = (Color.Unspecified)
                )
            }
            SpacerWidth(24.dp)
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .width(90.dp)
                    .height(55.dp),
                border = BorderStroke(0.7.dp, color = Color(0xFF9e9e9e)),
                shape = RoundedCornerShape(24)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.apple),
                    contentDescription = "apple icon",
                    modifier = Modifier.size(32.dp),
                    tint = (Color.Unspecified)
                )
            }
        }
        SpacerHeight(18.dp)
        Row{
            Text(text = "Bạn đã có tài khoản?" )
            SpacerWidth(8.dp)
            Text(text = "Đăng nhập", modifier = Modifier.clickable {navController.navigate("login_screen")}, color = Color(0xFF8E6CEF))
        }
        SpacerHeight(18.dp)

    }
}

}

