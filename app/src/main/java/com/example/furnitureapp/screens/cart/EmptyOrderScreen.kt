package com.example.furnitureapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureapp.R

@Composable
fun EmptyOrderScreen( navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize().padding(vertical = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Orders", fontSize = 20.sp, fontWeight = FontWeight.Bold,)
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.carts ),
            contentDescription = "Cart_icon",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "No Orders yet", fontSize = 16.sp, color = Color.Black )
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFB794F6)
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp).height(50.dp)
        ) {
            Text(text = "Explore Categories", color = Color.White)
        }
    }
}