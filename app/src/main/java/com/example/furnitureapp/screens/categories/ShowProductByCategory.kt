package com.example.furnitureapp.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furnitureapp.R
import com.example.furnitureapp.components.ProductEachRow
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.model.topSellingProductList

@Composable
fun ShowProductByCategory(modifier: Modifier = Modifier){
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        SpacerHeight(18.dp)
        Box(modifier = Modifier
            .clickable {  }
            .size(42.dp)
            .clip(CircleShape)

        ){
            Icon(
                painterResource(id = R.drawable.arrowleft2),
                contentDescription = "Back Icon",
                modifier = Modifier.size(20.dp).align(Alignment.Center))
        }

        SpacerHeight(24.dp)

        Text(text= "Bàn ăn ", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W600))

        SpacerHeight(24.dp)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(topSellingProductList, key = {it.id}){
                    ProductEachRow(data = it)
                }
            }
        )

    }

}