package com.example.furnitureapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.furnitureapp.navigation.FurnitureNavGraph
import com.example.furnitureapp.view.categories.ShowProductByCategory
import com.example.furnitureapp.view.product.ProductDetailScreen

import com.example.furnitureapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel :AuthViewModel by viewModels()
        setContent {

           FurnitureNavGraph(authViewModel = authViewModel)
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
