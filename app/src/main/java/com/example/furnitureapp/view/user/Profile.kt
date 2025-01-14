package com.example.furnitureapp.view.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.furnitureapp.R
import com.example.furnitureapp.components.CommonTitle
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.components.SpacerWidth
import com.example.furnitureapp.viewmodel.AuthState
import com.example.furnitureapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,

){
    LaunchedEffect(Unit) {
        authViewModel.CheckAuthStatus()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0XFFf9f9f9)),
    ){

        ProfileHeader(navController,authViewModel)
        SpacerHeight(12.dp)
        ProfileOrder()
        SpacerHeight(12.dp)
        LogoutButton(authViewModel)
    }
}

@Composable
fun ProfileHeader(navController: NavController,authViewModel: AuthViewModel){

    val authState = authViewModel.authState.observeAsState()

    val colors = listOf(Color(0xFFE7E2FA), Color(0xFFF3F0FD))

        SpacerHeight(12.dp)
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Brush.horizontalGradient(colors))
            .padding(20.dp),
            verticalAlignment = Alignment.Bottom
        ){

            if(authState.value is AuthState.HuyXacThuc){
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.BottomEnd
                ){
                    Row(

                    ){
                        Button  (onClick = {navController.navigate("login_screen") }, shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color(0xFFA18AEC),

                                )) {
                            Text(text = "Đăng nhập", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400))
                        }
                        SpacerWidth(8.dp)

                        OutlinedButton (onClick = { navController.navigate("register_screen") },
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Color.White),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color(0xFFA18AEC),
                                contentColor = Color.White)
                        ) {
                            Text(text = "Đăng ký", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400))
                        }
                    }
                }
            }
            if (authState.value is AuthState.XacThuc){
//               currentUser?.let{user->
//                   user.photoUrl?.let{
//                       AsyncImage(
//                           modifier = Modifier.size(64.dp).clip(CircleShape),
//                            model = ImageRequest.Builder(LocalContext.current)
//                                .data(it)
//                                .crossfade(true)
//                                .build(),
//                           contentDescription = "avatar",
//                           contentScale = ContentScale.Crop
//                       )
//                   }
//
//               }
                Image(
                    painterResource(id = R.drawable.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                SpacerWidth(16.dp)

                Box(
                    modifier = Modifier.weight(1f)
                ){
                    Column(verticalArrangement = Arrangement.SpaceBetween,) {
//                        currentUser?.let{user->
//                            user.displayName?.let{name->
                                Text(text = "name", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600))
                            //}

                        //}

                        SpacerHeight(6.dp)
                        Text(text = "Thành viên: ", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400))
                    }
                    Box(modifier = Modifier
                        .clickable {  }
                        .size(42.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)
                        .clickable {  },

                        ){
                        Icon(
                            painterResource(id = R.drawable.note_pen),
                            contentDescription = "Edit profile",
                            modifier = Modifier.size(24.dp).align(Alignment.Center))
                    }

                }
            }



        }
    }


@Composable
fun ProfileOrder(){
    val title = "Đơn hàng của tôi"

   Column(modifier = Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 20.dp)){
       CommonTitle(title)
       Row(modifier = Modifier
           .fillMaxWidth(),

           horizontalArrangement = Arrangement.SpaceBetween
       ){
           OrderStatusItem(iconId = R.drawable.wallet,text = "Chờ \nxác nhận")
           OrderStatusItem(iconId = R.drawable.wallet_income,text = "Chờ\n vận chuyển")
           OrderStatusItem(iconId = R.drawable.shipping_timed,text = "Chờ\n giao hàng")
           OrderStatusItem(iconId = R.drawable.comment_alt,text = "Chưa \nđánh giá")
           OrderStatusItem(iconId = R.drawable.truck_arrow_left,text = "Đổi trả \n& huỷ")


       }
   }
}

@Composable
fun OrderStatusItem(iconId :Int , text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = Color(0xFFA18AEC)
        )
        SpacerHeight(8.dp)
        Text(text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 14.sp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LogoutButton(authViewModel: AuthViewModel){

    val authState = authViewModel.authState.observeAsState()

    if(authState.value is AuthState.XacThuc){
        Row(
            modifier= Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ){
            OutlinedButton (
                modifier = Modifier.weight(1f).padding(horizontal = 20.dp),
                onClick = { authViewModel.Logout() },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =Color(0XFFf9f9f9),
                    contentColor = Color(0xFFA18AEC)
                )
            ) {
                Text(text = "Đăng xuất", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400))
            }
        }
    }





}