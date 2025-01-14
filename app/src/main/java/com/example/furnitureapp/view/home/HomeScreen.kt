package com.example.furnitureapp.view.home

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.furnitureapp.R
import com.example.furnitureapp.components.CommonTitle
import com.example.furnitureapp.components.ProductEachRow
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.model.Categories
import com.example.furnitureapp.model.Product
import com.example.furnitureapp.model.bannerList
import com.example.furnitureapp.model.topSellingProductList
import com.example.furnitureapp.viewmodel.CategoryViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel
) {

    val listOfProduct = productViewModel.listProduct
    val listOfCategories = categoryViewModel.listOfCategories

    LaunchedEffect(Unit) {
        productViewModel.getAllProduct()
        categoryViewModel.getAllCategory()
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        MySearchBar()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                SliderBanner()
                SpacerHeight(12.dp)
                CategoriesRow(navController,listOfCategories)
                NewProductRow(navController,listOfProduct)
                SpacerHeight(16.dp)
                TopSellingColumn(navController,listOfProduct)
            }
        }

    }

}

@Composable
fun MySearchBar() {
    var searchString by remember { mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchString,
                    onValueChange = { searchString = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable {
                        },
                    placeholder = {Text(text = "Tìm kiếm sản phẩm", style = TextStyle(fontSize = 15.sp))},
                    shape = RoundedCornerShape(24.dp),
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

            }
}

@Composable
fun CategoriesRow(navController: NavController, listOfCategories: List<Categories>?) {
    Column {
        CommonTitle("Danh mục sản phẩm")
        SpacerHeight(12.dp)

        if (listOfCategories.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow {
                items(listOfCategories, key = { it.ma_loai }) { category ->
                    CategoryEachRow(categories = category, navController = navController) {id, name ->
                        navController.navigate("show_product_by_category/$id/$name")
                    }
                }
            }
        }
    }
}


@Composable
fun CategoryEachRow(categories: Categories,
                    navController: NavController,
                    onCategoryClick: (Int,String) -> Unit,
) {
    val categoryId = categories.ma_loai
    val categoryName = categories.ten_loai
    Column(
        modifier = Modifier
            .padding(end = 12.dp)
            .width(64.dp)
            .clickable {
                onCategoryClick(categoryId,categoryName)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFf3f3f3)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bookshelf),
                contentDescription = "",
                modifier = Modifier.size(38.dp),
                contentScale = ContentScale.Fit
            )

        }
        Text(
            text = categories.ten_loai ?: "haha",
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
fun TopSellingColumn( navController: NavController,listOfProduct : List<Product>){
        CommonTitle("Bán chạy")
        SpacerHeight(16.dp)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().height(1000.dp),
                content = {
                    items(listOfProduct, key = {it.id}){ product->
                        ProductEachRow(product = product, navController = navController){
                                productId -> navController.navigate("show_product_by_id/$productId")
                        }
                    }
                }
            )

}

@Composable
fun NewProductRow(navController: NavController,listOfProduct : List<Product>){
    Column (){
        CommonTitle("Sản phẩm mới")
        SpacerHeight(16.dp)
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(listOfProduct, key = { it.id }) { product ->
                ProductEachRow(product = product,navController = navController){
                    navController.navigate("show_product_by_id/${product.id}")
                }
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
