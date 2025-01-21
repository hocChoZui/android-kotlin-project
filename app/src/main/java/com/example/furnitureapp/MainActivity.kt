package com.example.furnitureapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.furnitureapp.navigation.FurnitureNavGraph
import com.example.furnitureapp.viewmodel.CartViewModel

import com.example.furnitureapp.viewmodel.CategoryViewModel
import com.example.furnitureapp.viewmodel.GalleryViewModel
import com.example.furnitureapp.viewmodel.OrderViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel
import com.example.furnitureapp.viewmodel.UserViewModel

const val WEB_CLIENT_ID = "240237248871-81t4ino63s8as77siev3pi3cb7t754ld.apps.googleusercontent.com"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val userViewModel by viewModels<UserViewModel> ()
        val productViewModel by viewModels<ProductViewModel> ()
        val categoryViewModel by viewModels<CategoryViewModel> ()
        val galleryViewModel by viewModels<GalleryViewModel> ()
        val cartViewModel by viewModels<CartViewModel> ()
        val orderViewModel by viewModels<OrderViewModel> ()
        setContent {

           FurnitureNavGraph(
               userViewModel = userViewModel,
               productViewModel = productViewModel,
               categoryViewModel = categoryViewModel,
               galleryViewModel = galleryViewModel,
               cartViewModel = cartViewModel,
               orderViewModel = orderViewModel
           )
          //GreetingPreview()
        }
    }
}

//@Composable
//fun TestScreen(){
//    ProductDetailScreen()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestScreen()
//}
