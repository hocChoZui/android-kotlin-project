package com.example.furnitureapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.furnitureapp.MainScreen
import com.example.furnitureapp.screens.cart.CartScreen
import com.example.furnitureapp.screens.cart.EmptyCart
import com.example.furnitureapp.screens.home.HomeScreen
import com.example.furnitureapp.screens.order.EmptyOrders
import com.example.furnitureapp.screens.order.OrderDetailsScreen
import com.example.furnitureapp.screens.order.OrderSummaryScreen
import com.example.furnitureapp.screens.order.OrdersScreen
import com.example.furnitureapp.screens.user.ProfileScreen
import com.example.furnitureapp.screens.welcome.LoginScreen
import com.example.furnitureapp.screens.welcome.RegisterScreen
import com.example.furnitureapp.viewmodel.AuthViewModel


@Composable
fun FurnitureNavGraph(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route

    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(modifier, navController, authViewModel)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(modifier, navController)
        }
        composable(route = Screen.EmptyOrderScreen.route) {
            EmptyOrders(modifier,navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(modifier, navController, authViewModel)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(modifier,navController,authViewModel)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(modifier, navController,authViewModel)
        }
        composable(route = Screen.Order.route) {
            OrdersScreen(modifier,navController)
        }
        composable(route = Screen.EmptyCart.route)  {
            EmptyCart(modifier,navController)
        }
        composable(route = Screen.Cart.route) {
            CartScreen(modifier,navController)
        }
        composable(route = Screen.OrderSummary.route) {
            OrderSummaryScreen(modifier,navController)
        }
        composable(route = Screen.OrderDetail.route) {
            OrderDetailsScreen(modifier,navController)
        }
    }
}