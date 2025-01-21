package com.example.furnitureapp.view.order

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureapp.R
import com.example.furnitureapp.components.BackButton
import com.example.furnitureapp.components.CommonTitle
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.components.SpacerWidth
import com.example.furnitureapp.model.Order
import com.example.furnitureapp.viewmodel.OrderViewModel
import com.example.furnitureapp.viewmodel.UserViewModel
import java.text.Normalizer
import java.text.NumberFormat
import java.util.Locale
import java.util.regex.Pattern


@Composable
fun OrdersScreen(
    userId: Int,
    modifier: Modifier = Modifier,
    navController: NavController,
    orderViewModel: OrderViewModel,
) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(userId) {
        orderViewModel.getOrderInfo(userId)
    }
    val items = orderViewModel.listOfOrderItem
    items.forEach { order ->
        Log.d("Order trạng thái:",order.trang_thai)
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        SpacerHeight(18.dp)
        BackButton(onClick = {navController.popBackStack()})

        CommonTitle("Đơn hàng")
//        LazyRow(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(tabTitles.size) { index ->
//                val title = tabTitles[index]
//                Button(
//                    onClick = { selectedTabIndex = index },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.LightGray,
//                        contentColor = Color.White
//                    ),
//                    shape = RoundedCornerShape(16.dp),
//                    modifier = Modifier
//                        .width(120.dp)
//                        .height(60.dp)
//                ) {
//                    Text(
//                        text = title,
//                        fontSize = 16.sp,
//                        maxLines = 1,
//                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis, // Hiển thị "..." nếu văn bản quá dài
//                        modifier = Modifier.fillMaxWidth(),
//                        color = if (selectedTabIndex == index) Color.White else Color.Black
//                    )
//                }
//            }
//        }

        if (items.isEmpty()) {
            EmptyOrders()
        } else {

            LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 10.dp)) {
                items(items) { order ->
                    OrderCard(userId,order){userId,orderId ->
                        navController.navigate("order_detail/$userId/$orderId")
                    }
                }
            }
        }
    }
}


@Composable
fun OrderCard(userId: Int,order: Order,onClick : (Int,Int) -> Unit) {
    val price = NumberFormat.getInstance(Locale("vi", "VN")).format(order.tong_tien)
    val orderId = order.id
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable { onClick(userId,orderId) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Đơn hàng #404Er${order.id}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${order.so_luong} mặt hàng",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End){
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
            Text(
                text = "${price} đ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

    }
}

@Composable
fun EmptyOrders(modifier: Modifier = Modifier) {
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
            Text(
                text = "Bạn không có đơn hàng nào!",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
