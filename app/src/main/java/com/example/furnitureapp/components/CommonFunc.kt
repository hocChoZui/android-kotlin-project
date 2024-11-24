package com.example.furnitureapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furnitureapp.R
import com.example.furnitureapp.model.TopSellingProduct

@Composable
fun SpacerWidth(width: Dp = 10.dp){
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun SpacerHeight(height : Dp = 10.dp){
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun ProductEachRow(data : TopSellingProduct,
                   modifier: Modifier = Modifier,
                   onClick : () -> Unit = {}
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFf3f3f3))
            .clickable { onClick()}
    ){
        Column {
            Box{
                Image(
                    painter = painterResource(id = data.imageResId),
                    contentDescription = data.name,
                    modifier = Modifier
                        .width(160.dp)
                        .height(200.dp)
                        .padding(start = 12.dp, end = 12.dp),

                    )

                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(18.dp)
                            .align(Alignment.TopEnd),
                        tint = Color.Unspecified
                    )

            }

            Column(modifier= Modifier
                .width(160.dp)
                .padding(8.dp)
                .height(40.dp),

                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = data.name,
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black,

                        )

                )
                Text(text = data.price,
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    )
                )

            }
        }
    }
}

@Composable
fun BackBtnAndTitle(modifier: Modifier = Modifier,title: String){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Box(modifier = Modifier
            .clickable {  }
            .size(42.dp)
            .clip(CircleShape)
            .align(Alignment.CenterStart)
            .clickable {  },


        ){
            Icon(
                painterResource(id = R.drawable.arrowleft2),
                contentDescription = "Back Icon",
                modifier = Modifier.size(24.dp).align(Alignment.Center),)
        }

        Text(
            text = title,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W600),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun CommonTitle(title : String , onClick : () -> Unit = {}){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = title,style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ))
        TextButton(
            onClick = onClick
        ) {
            Text(text = "Xem tất cả",style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black
            ))
            SpacerWidth(3.dp)
            Icon(painter = painterResource(id = R.drawable.arrowright2), contentDescription = "SeeAll",modifier = Modifier.size(18.dp))
        }
    }
}