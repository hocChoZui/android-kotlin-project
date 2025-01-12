package com.example.furnitureapp.view.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun OrdersScreen(modifier: Modifier = Modifier,navController: NavController) {
    val tabs = listOf("Đang xử lý", "Đã giao", "Đã nhận", "Đã trả lại", "Đã hủy")
    val orders = listOf(
        Order("456765", 4),
        Order("454569", 2),
        Order("454809", 1)
    )

    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(20.dp))

        // Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab (
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(16.dp),
                        color = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 10.dp)) {
            items(orders) { order ->
                OrderCard(order)
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .padding(16.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Đơn hàng #${order.id}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "${order.items} mặt hàng", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
    }
}

data class Order(val id: String, val items: Int)
val orders = listOf(
    Order("456765", 4),
    Order("454569", 2),
    Order("454809", 1)
)

