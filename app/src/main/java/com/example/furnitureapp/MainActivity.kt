package com.example.furnitureapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.furnitureapp.navigation.FurnitureNavGraph
import com.example.furnitureapp.screens.categories.ShowProductByCategory
import com.example.furnitureapp.screens.home.HomeScreen
import com.example.furnitureapp.screens.user.ProfileScreen

import com.example.furnitureapp.screens.welcome.LoginScreen
import com.example.furnitureapp.screens.welcome.RegisterScreen

import com.example.furnitureapp.ui.theme.FurnitureAppTheme
import com.example.furnitureapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel :AuthViewModel by viewModels()
        setContent {

            FurnitureNavGraph(authViewModel = authViewModel)
//            GreetingPreview()
        }
    }
}

//@Composable
//fun TestScreen(){
//    ProfileScreen(modifier = Modifier)
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestScreen()
//}
