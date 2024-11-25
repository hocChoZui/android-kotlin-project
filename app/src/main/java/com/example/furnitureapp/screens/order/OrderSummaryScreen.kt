package com.example.furnitureapp.screens.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureapp.screens.cart.SummaryRow

@Composable
fun OrderSummaryScreen(modifier: Modifier = Modifier,navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle back action */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Quay lại",
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "CheckOut",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Shipping Address Section
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Địa chỉ giao hàng",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "2715 Ash Dr. San Jose, South Dakota...",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "",
                    tint = Color.Gray
                )
            }
        }

        // Payment Method Section
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Phương thức thanh toán",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "**** 4187",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Order Summary Section
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 25.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SummaryRow(label = "Tổng tiền hàng", value = "$200")
                SummaryRow(label = "Tổng phí vận chuyển", value = "$8.00")
                SummaryRow(label = "Thuế", value = "$0.00")
                SummaryRow(label = "Tổng thanh toán", value = "$208", bold = true)

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* Checkout action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$208",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Đặt hàng",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OrderSummaryItem(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = if (isBold)
                MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            else
                MaterialTheme.typography.bodyMedium
        )
    }
}
