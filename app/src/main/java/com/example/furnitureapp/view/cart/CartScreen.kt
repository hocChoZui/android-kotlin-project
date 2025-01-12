package com.example.furnitureapp.view.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.example.furnitureapp.R

@Composable
fun CartScreen(modifier: Modifier = Modifier,navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Quay lại",
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .padding(top = 20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = "Giỏ hàng", style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(top = 28.dp)
            )
            Text(
                text = "Xóa tất cả",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red),
                modifier = Modifier.clickable { /* Remove all action */ }
                    .align(Alignment.CenterVertically)
                    .padding(top = 85.dp)

            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(cartItems) { item ->
                CartItemRow(item = item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SummaryRow(label = "Tổng tiền hàng", value = "$200")
            SummaryRow(label = "Tổng phí vận chuyển", value = "$8.00")
            SummaryRow(label = "Thuế", value = "$0.00")
            SummaryRow(label = "Tổng thanh toán", value = "$208", bold = true)

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nhập mã Voucher",
                    modifier = Modifier.weight(1f),
                    color = Color.Gray
                )
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Checkout action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA))
            ) {
                Text(text = "Thanh toán", color = Color.White)
            }
        }

    }
}

@Composable
fun CartItemRow(item: CartItem) {
    val updatedItem = remember { item.copy() }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Decrease quantity */ }) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "")
            }
            Text(text = "${item.quantity}", style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = { /* Increase quantity */ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, bold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = if (bold) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            else MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = if (bold) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            else MaterialTheme.typography.bodyMedium
        )
    }
}

data class CartItem(
    val name: String,
    val imageRes: Int,
    val quantity: Int
)

val cartItems = listOf(
    CartItem("Giường", R.drawable.double_bed, 1),
    CartItem("Sofa", R.drawable.seater_sofa, 1)
)
