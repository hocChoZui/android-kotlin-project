package com.example.furnitureapp.screens.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OrderDetailsScreen(modifier: Modifier = Modifier,navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            OrderStatusItem(status = "Đã giao", date = "28 Tháng 5", isChecked = false)
            OrderStatusItem(status = "Đã xuất kho", date = "28 Tháng 5", isChecked = true)
            OrderStatusItem(status = "Đã xác nhận", date = "28 Tháng 5", isChecked = true)
            OrderStatusItem(status = "Đã đặt hàng", date = "28 Tháng 5", isChecked = true)
        }

        Spacer(modifier = Modifier.height(16.dp))


        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Các mặt hàng trong đơn hàng",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "4 sản phẩm")
                }
                Text(
                    text = "Xem tất cả",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable { }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Thông tin giao hàng", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "2715 Ash Dr. San Jose, South Dakota 83475")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "121-224-7890")
            }
        }
    }
}

@Composable
fun OrderStatusItem(status: String, date: String, isChecked: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        Icon(
            imageVector = if (isChecked) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
            contentDescription = null,
            tint = if (isChecked) MaterialTheme.colorScheme.primary else Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = status, style = MaterialTheme.typography.bodyLarge)
            Text(text = date, style = MaterialTheme.typography.bodySmall)
        }
    }
}
