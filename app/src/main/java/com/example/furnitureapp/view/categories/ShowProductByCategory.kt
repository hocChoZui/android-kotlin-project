package com.example.furnitureapp.view.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureapp.R
import com.example.furnitureapp.components.BackButton
import com.example.furnitureapp.components.ProductEachRow
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.model.Product
import com.example.furnitureapp.retrofit.GalleryAPIService
import com.example.furnitureapp.viewmodel.GalleryViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun ShowProductByCategory(id: Int, navController: NavController,
                          productViewModel: ProductViewModel,
                          name : String,

){
    val categoryName = name;

    productViewModel.getProductByCategoryId(id)

    val listProductByCateId = productViewModel.listProductByCategoryId

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        SpacerHeight(18.dp)
        BackButton{navController.popBackStack()}
        Text(text= categoryName, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W600))
        SpacerHeight(18.dp)
            if(listProductByCateId.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Không có sản phẩm nào.",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400)
                    )
                }
            }else{
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        items(listProductByCateId, key = {it.id}){ product->
                            ProductEachRow(product = product, navController = navController){
                                    productId -> navController.navigate("show_product_by_id/$productId")
                            }
                        }
                    }
                )
            }


    }

}