package com.example.furnitureapp.view.cart

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
import com.example.furnitureapp.model.Product
import com.example.furnitureapp.viewmodel.CartViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CartScreen(userId : Int,
               modifier: Modifier = Modifier,
               navController: NavController,
               cartViewModel: CartViewModel,
               productViewModel: ProductViewModel
) {

    LaunchedEffect(Unit) {
        cartViewModel.getCartInfo(userId )
        productViewModel.getAllProduct()
    }


    val items = cartViewModel.listOfProductItems
    val listOfProduct = productViewModel.listProduct



    val priceEachItem = items.map { it.tong_tien.toDouble() ?: 0.0}
    var totalPrice : Double = 0.0

    priceEachItem.forEach{price ->
        totalPrice += price
    }
    val price = NumberFormat.getInstance(Locale("vi", "VN")).format(totalPrice)

    if(items.isEmpty()){
        EmptyCart()
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                BackButton { navController.popBackStack() }
                Text(
                    text = "Giỏ hàng",
                    style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(items, key = {it.id}) { item ->
                   CartItemRow(item = item,
                       listOfProduct = listOfProduct,
                       onQuantityChange = { cartItem, newQuantity ->
                           //cartViewModel.updateCartItemQuantity(cartItem.id, newQuantity)
                       },
                       onRemoveItem = { cartItem ->
                           cartViewModel.deleteProduct(1,8)
                           Log.d("DelCartViewModel ", cartViewModel.cartDelResult.toString())
                       }
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

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {  cartViewModel.deleteProduct(1,15)},
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


}

@Composable
fun CartItemRow(
    item: Cart,
    listOfProduct: List<Product>,
    onQuantityChange: (Cart, Int) -> Unit,
    onRemoveItem: (Cart) -> Unit
) {
    val product = listOfProduct.find { it.id == item.ma_san_pham }

    if (product != null) {
        var showRemoveDialog by remember { mutableStateOf(false) }
        var quantity by remember { mutableStateOf(item.so_luong) }

        if (showRemoveDialog) {
            AlertDialog(
                onDismissRequest = { showRemoveDialog = false },
                title = { Text("Xóa sản phẩm") },
                text = { Text("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng không?") },
                confirmButton = {
                    TextButton(onClick = {
                        onRemoveItem(item)
                        showRemoveDialog = false
                    }) {
                        Text("Xóa")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showRemoveDialog = false }) {
                        Text("Hủy")
                    }
                }
            )
        }

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
                IconButton(onClick = {
                    if (quantity > 1) {
                        quantity -= 1
                        onQuantityChange(item.copy(so_luong = quantity), quantity)
                    } else {
                        showRemoveDialog = true
                    }
                }) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease")
                }
                Text(text = "${item.so_luong}", style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = {
                    if (quantity < product.so_luong) {
                        quantity += 1
                        onQuantityChange(item.copy(so_luong = quantity), quantity)
                    }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Increase")
                }
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
fun EmptyCart(modifier: Modifier = Modifier) {
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
            Text(text = "Không có sản phẩm nào trong giỏ hàng", fontSize = 16.sp, color = Color.Black, fontWeight =  FontWeight.Bold)
            SpacerHeight(20.dp)
        }
    }
}
