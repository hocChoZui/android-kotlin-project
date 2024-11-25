package com.example.furnitureapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.furnitureapp.components.BottomNavBar
import com.example.furnitureapp.model.NavBarItem
import com.example.furnitureapp.screens.home.EmptyOrderScreen
import com.example.furnitureapp.screens.home.HomeScreen
import com.example.furnitureapp.screens.user.ProfileScreen
import com.example.furnitureapp.viewmodel.AuthViewModel


@Composable
fun MainScreen( modifier: Modifier = Modifier,navController: NavController,authViewModel: AuthViewModel){

    var selectedIndex by remember { mutableIntStateOf(0) }
    val navBarItems = listOf(
        NavBarItem("Home", R.drawable.house_blank),
        NavBarItem("Cart", R.drawable.shopping_cart),
        NavBarItem("Order", R.drawable.receipt),
        NavBarItem("Notification", R.drawable.bell),
        NavBarItem("Profile", R.drawable.user)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(

                navBarItems = navBarItems,
                selectedIndex = selectedIndex,
                onNavigationItemSelected = { index ->
                    selectedIndex = index
                }
            )
        }
    ) {
        innerPadding->
        ContentScreen(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            selectedIndex ,authViewModel)
    }
}

@Composable
fun ContentScreen(navController: NavController,modifier: Modifier = Modifier, selectedIndex: Int ,authViewModel: AuthViewModel) {
    when(selectedIndex){
        0-> HomeScreen(modifier,navController )
        1->EmptyOrderScreen(navController)
        4-> ProfileScreen(modifier,navController,authViewModel)
    }
}
