package com.example.furnitureapp.screens.user

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureapp.R
import com.example.furnitureapp.components.CommonTitle
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.components.SpacerWidth

@Composable
fun ProfileScreen(modifier: Modifier = Modifier,navController: NavController){

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0XFFf9f9f9)),
    ){

        ProfileHeader(navController)
        SpacerHeight(12.dp)
        ProfileOrder()
    }
}

@Composable
fun ProfileHeader(navController: NavController){
    val colors = listOf(Color(0xFFE7E2FA), Color(0xFFF3F0FD))



        SpacerHeight(12.dp)
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Brush.horizontalGradient(colors))
            .padding(20.dp),
            verticalAlignment = Alignment.Bottom
        ){
            Image(
                painterResource(id = R.drawable.avatar),
                contentDescription = "avatar",
                modifier = Modifier.size(68 .dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            SpacerWidth(16.dp)
            // Sau khi đang nhap thanh cong
//    Box(
//    modifier = Modifier.weight(1f)
//    ){
//        Column(verticalArrangement = Arrangement.SpaceBetween,) {
//            Text(text = "Username", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600))
//            SpacerHeight(6.dp)
//            Text(text = "Thành viên: ", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400))
//        }
//        Box(modifier = Modifier
//            .clickable {  }
//            .size(42.dp)
//            .clip(CircleShape)
//            .align(Alignment.CenterEnd)
//            .clickable {  },
//
//            ){
//            Icon(
//                painterResource(id = R.drawable.note_pen),
//                contentDescription = "Edit profile",
//                modifier = Modifier.size(24.dp).align(Alignment.Center))
//        }
//
//    }

            //Truoc khi dang nhap

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
    }


@Composable
fun ProfileOrder(){
    val title = "Đơn hàng của tôi"

   Column(modifier = Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 20.dp)){
       CommonTitle(title)
       Row(modifier = Modifier
           .fillMaxWidth()
           .padding(16.dp),
           horizontalArrangement = Arrangement.SpaceBetween
       ){
           OrderStatusItem(iconId = R.drawable.wallet,text = "Chờ \nthanh toán")
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