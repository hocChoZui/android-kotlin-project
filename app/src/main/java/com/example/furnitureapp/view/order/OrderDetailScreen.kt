package com.example.furnitureapp.view.order

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.furnitureapp.R
import com.example.furnitureapp.components.BackButton
import com.example.furnitureapp.components.SpacerHeight
import com.example.furnitureapp.model.Cart
import com.example.furnitureapp.model.Order
import com.example.furnitureapp.model.Product
import com.example.furnitureapp.viewmodel.CartViewModel
import com.example.furnitureapp.viewmodel.OrderViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun OrderDetailScreen(userId: Int,
                        orderId:Int,
               modifier: Modifier = Modifier,
               navController: NavController,
               productViewModel: ProductViewModel,
               orderViewModel: OrderViewModel
) {

    LaunchedEffect(Unit) {
       orderViewModel.getOrderInfo(userId)
    }

    val items = orderViewModel.listOfOrderItem

    val item = items.find{ it.id == orderId}

    val listOfProduct = productViewModel.listProduct

    val priceEachItem = items.map { it.tong_tien.toDouble() ?: 0.0}
    var totalPrice : Double = 0.0

    priceEachItem.forEach{price ->
        totalPrice += price
    }
    val price = NumberFormat.getInstance(Locale("vi", "VN")).format(totalPrice)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            BackButton { navController.popBackStack() }
            if(item == null){
                EmptyOrder()
            }
            else{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {


                    Text(
                        text = "Chi tiết đơn hàng",
                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                Text(
                    text = "Trạng thái: ${item.trang_thai.toString()}",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Light, color = Color.Magenta),
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(items, key = {it.id}) { item ->
                        OrderItemRow(item = item,
                            listOfProduct = listOfProduct,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 45.dp)
                ) {
                    SummaryRow(label = "Tổng tiền hàng", value = "${price} đ")
                    SpacerHeight(8.dp)
                    SummaryRow(label = "Tổng phí vận chuyển", value = "Miễn phí")
                    SpacerHeight(8.dp)
                    SummaryRow(label = "Tổng thanh toán", value = "${price} đ", bold = true)


                }
            }

        }
    }


@Composable
fun OrderItemRow(
    item: Order,
    listOfProduct: List<Product>,
) {
    val product = listOfProduct.find { it.id == item.ma_san_pham }

    if (product != null) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.anh_dai_dien,
                contentDescription = product.ten_san_pham,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.ten_san_pham,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Số lượng: ${item.so_luong}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                val giaSp =  product.gia
                val sp = NumberFormat.getInstance(Locale("vi", "VN")).format(giaSp)
                Text(text = "${sp} đ", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, bold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = if (bold) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            else MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value.toString(),
            style = if (bold) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            else MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun EmptyOrder(modifier: Modifier = Modifier) {
    Box(
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
                contentDescription = "Cart_icon",
                modifier = Modifier.size(100.dp)
            )
            SpacerHeight(20.dp)
            Text(text = "Không có đơn", fontSize = 16.sp, color = Color.Black, fontWeight =  FontWeight.Bold)
            SpacerHeight(20.dp)
        }
    }
}
