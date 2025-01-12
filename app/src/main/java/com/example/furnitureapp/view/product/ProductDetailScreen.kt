package com.example.furnitureapp.view.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureapp.R
import com.example.furnitureapp.components.BackButton
import com.example.furnitureapp.components.CommonTitle
import com.example.furnitureapp.components.ProductEachRow
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.components.SpacerWidth
import com.example.furnitureapp.model.Categories
import com.example.furnitureapp.model.TopSellingProduct
import com.example.furnitureapp.model.bannerList
import com.example.furnitureapp.model.categoriesList
import com.example.furnitureapp.model.topSellingProductList
import com.example.furnitureapp.view.home.PageIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import androidx.compose.material3.Scaffold

@Composable
fun ProductDetailScreen(productId: Int ,navController: NavController) {
    val product = topSellingProductList.find { it.id == productId }

    Scaffold(
        modifier = Modifier.padding(top = 12.dp, start = 8.dp, end = 8.dp, bottom = 4.dp),
        containerColor =  Color.LightGray,
        topBar = {

            TopBar(
                onBackClick = {navController.popBackStack()},
                onCartClick = {},
                onMoreClick = {},

            )
        },
        bottomBar = {
            AddToCartButton(
                onAddClick = {},
                onChatClick = {},
            )
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {

                ImageSlider()
                ProductTitle(productId)
                SpacerHeight(16.dp)
                RelatedProducts(navController)
                SpacerHeight(16.dp)
                ProductDescription()
            }
        }
    }
}


@Composable
fun ImageSlider() {
    val pagerState = rememberPagerState(pageCount = { categoriesList.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(406.dp)
            .background(color = Color(0XFFffffff))
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(300.dp)
        ) { currentPage ->
            Image(
                painter = painterResource(id = categoriesList[currentPage].cateResId),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            items(categoriesList, key = { it.cateResId }) {
                ImageEachRow(categories = it, pagerState = pagerState)
            }
        }
    }
}


@Composable
fun ImageEachRow(categories: Categories,pagerState:PagerState){
    val index = categoriesList.indexOf(categories)
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(end = 12.dp)
            .width(64.dp)
            .height(76.dp)
            .border(0.5.dp, Color.Gray,)
            .clickable {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = categories.cateResId),
                contentDescription = "",
                modifier = Modifier.size(54.dp),
                contentScale = ContentScale.Fit
            )

        }
    }
}

@Composable
fun TopBar(
    onBackClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0XFFffffff)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(onClick = onBackClick)

        Row (){
            IconButton(onClick = onCartClick) {
                Icon(painter = painterResource(id = R.drawable.shopping_cart),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(20.dp)
                )
            }

            IconButton(onClick = onMoreClick) {
                Icon(painter = painterResource(id = R.drawable.ellipsis),
                    contentDescription = "More Options",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun ProductTitle(productId: Int) {
    val product = topSellingProductList.find { it.id == productId }
//    val originalPrice = if (product.price.toInt() > 0) {
//
//        product.price / (1 - product.discount / 100)
//    } else {
//        product.price
//    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0XFFffffff)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(3.5f).padding(4.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "${product?.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )


                }
                SpacerHeight(4.dp)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Đánh giá",
                            tint = Color(0xFFFFD700), // Màu vàng
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    SpacerWidth(4.dp)

                    Text(
                        text = "4.8",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    SpacerWidth(2.dp)

                    Text(
                        text = "(653)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.5f).padding(4.dp),
            ) {
                Row {
                    IconButton(onClick = { }) {
                        Icon(
                            painterResource(R.drawable.heart),
                            contentDescription = "Yêu thích",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }


                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 0.8.dp,
            color = Color.LightGray,
        )
        Row(
            modifier = Modifier
                .height(46.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.width(72.dp).height(46.dp).padding(4.dp)
                    .background(color = Color(0XFFEDEDED)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "-46%",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0XFFEF683A)

                    )
                )
            }
            SpacerWidth(12.dp)
            Text(
                text = "${product?.price}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0XFFEF683A)

                )
            )
            SpacerWidth(12.dp)
            Text(
                text = "${product?.price}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )
            )
        }
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 0.8.dp,
            color = Color.LightGray,
        )
        Row(
            modifier = Modifier
                .height(46.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            SpacerWidth(4.dp)
            Icon(
                painterResource(R.drawable.check),
                contentDescription = "Yêu thích",
                tint = Color(0XFFef76f1),
                modifier = Modifier.size(20.dp)
            )
            SpacerWidth(18.dp)
            Text(
                text = "Trả hàng miễn phí 15 ngày",
                style = MaterialTheme.typography.bodyMedium,
            )


        }
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 0.8.dp,
            color = Color.LightGray,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 12.dp, bottom = 12.dp)

        ) {
            Text(
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.Black
                        )
                    ) {
                        append("Kích thước:")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.Black
                        )
                    ) {
                        append(" Dài 52cm x Rộng 49cm x Cao đến đệm ngồi/lưng tựa 46cm/74cm")
                    }


                }
            )
            SpacerHeight(12.dp)
            Text(
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.Black
                        )
                    ) {
                        append("Chất liệu:")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.Black,

                        )
                    ) {
                        append("\n")
                        append("- Gỗ cao su tự nhiên\n")
                        append("- Vải bọc polyester chống nhăn, kháng bụi bẩn và nấm mốc\n")
                        append("Chống thấm, cong vênh, trầy xước, mối mọt")
                    }


                },
                style = TextStyle(
                    lineHeight = 24.sp
                )
            )

        }
        QuantitySelector(1, onDecrease = {}, onIncrease = {})
    }
}

@Composable
fun RelatedProducts(navController: NavController){
    Column ( modifier = Modifier.background(color = Color(0XFFffffff))){
        CommonTitle("Có thể bạn thích")
        SpacerHeight(16.dp)
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(topSellingProductList, key = { it.id }) {
                ProductEachRow(data = it, navController = navController){
                        productId -> navController.navigate("show_product_by_id/$productId")
                }
            }
        }
    }
}

@Composable
fun ProductDescription(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0XFFffffff)),
    ){
        SpacerHeight(12.dp)
        Text(text = "Mô tả sản phẩm",style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            color = Color.DarkGray
        ))
        SpacerHeight(12.dp)

    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)

    ) {
        Box(modifier = Modifier
            .size(36.dp)
            .background(color = Color(0XFFf5f5f5))){
            IconButton(onClick =onDecrease) {
                Icon(painter = painterResource(id = R.drawable.minus),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

       Box(modifier = Modifier
           .width(100.dp)
           .height(36.dp)
           .border(width = 0.5.dp, color = Color.LightGray)
           , contentAlignment = Alignment.Center,
       ){
           Text(
               text = "$quantity",
               style = TextStyle(
                   fontSize = 18.sp,
                   fontWeight = FontWeight.Bold,
                   color = Color.Black
               )
           )
       }

        // Increment Button
        Box(modifier = Modifier
            .size(36.dp)
            .background(color = Color(0XFFf5f5f5))
        ){
            IconButton(onClick =onIncrease) {
                Icon(painter = painterResource(id = R.drawable.add),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
@Composable
fun AddToCartButton(onAddClick: () -> Unit ,onChatClick: () -> Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = Color(0XFFffffff)),
    ) {
        Box(modifier = Modifier
            .width(64.dp),
            contentAlignment = Alignment.Center
            ){
            IconButton(onClick = onChatClick) {
                Icon(painter = painterResource(id = R.drawable.messenger),
                    contentDescription = "Chat",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
       Button(
           onClick = onAddClick,
           modifier = Modifier
               .fillMaxWidth()
               .height(48.dp).padding(start = 12.dp, end = 12.dp)
             ,
           shape = RoundedCornerShape(12.dp),
           colors = ButtonDefaults.buttonColors(
               containerColor = Color(0XFFEF683A),

           )
       ) {
           Icon(painter = painterResource(R.drawable.shopping_cart),
               contentDescription = "add to cart")
            SpacerWidth(8.dp)
           Text(text = "Thêm vào giỏ",style = TextStyle(
               fontSize = 16.sp,
               fontWeight = FontWeight.W500,
               color = Color.White
           ))
       }
    }
}
