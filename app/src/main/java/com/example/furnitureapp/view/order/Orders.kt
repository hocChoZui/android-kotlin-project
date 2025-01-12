package com.example.furnitureapp.view.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.furnitureapp.components.SpacerHeight

@Composable
fun EmptyOrders(modifier: Modifier = Modifier,navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.carts),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            SpacerHeight(20.dp)
            Text(text = "No Orders yet", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            SpacerHeight(20.dp)
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFFB794F6)
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(50.dp)
            ) {
                Text(text = "Explore Categories", color = Color.White)
            }
        }
    }


}
