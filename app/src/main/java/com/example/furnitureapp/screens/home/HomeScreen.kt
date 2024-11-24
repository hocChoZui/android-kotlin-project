package com.example.furnitureapp.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.furnitureapp.R
import com.example.furnitureapp.components.CommonTitle
import com.example.furnitureapp.components.ProductEachRow
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.components.SpacerWidth
import com.example.furnitureapp.model.Categories
import com.example.furnitureapp.model.bannerList
import com.example.furnitureapp.model.categoriesList
import com.example.furnitureapp.model.topSellingProductList
import com.example.furnitureapp.viewmodel.AuthViewModel
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White)
    ) {
        item {
            MySearchBar(navController)
            SliderBanner()
            SpacerHeight(12.dp)
            CategoriesRow()
            TopSellingRow()
            NewProductRow()
            SpacerHeight(80.dp)
        }
    }
}




@Composable
fun MySearchBar(navController: NavController) {
    var searchString by remember { mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(108.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchString,
                    onValueChange = { searchString = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                        .height(52.dp)
                        .clickable {
                        },
                    shape = RoundedCornerShape(24.dp),
                    placeholder = { Text(text = "Bạn muốn tìm gì?",) },
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color(0xFFf4f4f4),
                        focusedContainerColor = Color(0xFFf4f4f4),

                    ),

                    leadingIcon = {
                        Icon(
                            painter = painterResource(id =R.drawable.search),
                            contentDescription = "Search",
                        )
                    }
                )

                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("login_screen")
                        },
                    contentScale = ContentScale.Crop,
                    )
                Spacer(modifier = Modifier.width(8.dp))
            }

}

@Composable
fun CategoriesRow(){
    Column {
        CommonTitle("Danh mục sản phẩm")
        SpacerHeight(12.dp)
        LazyRow (

        ){
        items(categoriesList, key = {it.cateResId}) {
            CategoryEachRow(categories = it)
        }
        }
    }
}

@Composable
fun CategoryEachRow(categories: Categories) {
    Column(
        modifier = Modifier
            .padding(end = 12.dp)
            .width(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFDFDFDF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = categories.cateResId),
                contentDescription = "",
                modifier = Modifier.size(38.dp),
                contentScale = ContentScale.Fit
            )

        }
        Text(
            text = categories.name,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black
            ),
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }
}




@Composable
fun TopSellingRow(){
    Column{
        CommonTitle("Bán chạy")
        SpacerHeight(16.dp)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)){
                items(topSellingProductList, key = {it.id}){
                    ProductEachRow(data = it, modifier = Modifier.padding(end = 12.dp, bottom = 12.dp))
                }
            }
    }
}

@Composable
fun NewProductRow(){
    Column {
        CommonTitle("Sản phẩm mới")
        SpacerHeight(16.dp)
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(topSellingProductList, key = { it.id }) {
                ProductEachRow(data = it)
            }
        }
    }
}



@Composable
fun SliderBanner(){
    val pagerState = rememberPagerState(pageCount =
    { bannerList.size }
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
           val nextPager = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPager)
        }
    }

    Column (modifier =  Modifier
            .fillMaxWidth()
            .height(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier.wrapContentSize()){
                HorizontalPager(state =  pagerState,
                    modifier = Modifier.wrapContentSize()

                    ) {
                    currentPage ->
                    Card(modifier = Modifier.wrapContentSize(),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ){
                        Image(painter = painterResource(id = bannerList[currentPage].imageResId),contentDescription = "")

                    }
                }
                PageIndicator(
                    pageCount = bannerList.size,
                    currentPage = pagerState.currentPage,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 4.dp)
                )

            }

    }

}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        repeat(pageCount) {
            IndicatorsDot(isSelected = it == currentPage,modifier = modifier)
        }

    }
}

@Composable
fun IndicatorsDot(isSelected: Boolean, modifier: Modifier) {
    val size = animateDpAsState(targetValue = if (isSelected) 10.dp else 6.dp,label = "")
Box(modifier = modifier
    .padding(2.dp)
    .size(size.value)
    .clip(CircleShape)
    .background(if (isSelected) Color(0xFF896de7) else Color(0xFFc9c9c9))

)
}
