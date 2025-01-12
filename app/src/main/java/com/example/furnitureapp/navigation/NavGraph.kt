package com.example.furnitureapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.furnitureapp.MainScreen
import com.example.furnitureapp.view.cart.CartScreen
import com.example.furnitureapp.view.cart.EmptyCart
import com.example.furnitureapp.view.categories.ShowProductByCategory
import com.example.furnitureapp.view.home.HomeScreen
import com.example.furnitureapp.view.order.EmptyOrders
import com.example.furnitureapp.view.order.OrderDetailsScreen
import com.example.furnitureapp.view.order.OrderSummaryScreen
import com.example.furnitureapp.view.order.OrdersScreen
import com.example.furnitureapp.view.product.ProductDetailScreen
import com.example.furnitureapp.view.user.ProfileScreen
import com.example.furnitureapp.view.welcome.LoginScreen
import com.example.furnitureapp.view.welcome.RegisterScreen
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
        composable(route = Screen.ShowProductByCategoryName.route+ "/{categoryName}",
                arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) {backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            ShowProductByCategory(categoryName, navController)

        }
        composable(route = Screen.ProductDetailScreen.route + "/{productId}",
            arguments = listOf(navArgument("productId"){type = NavType.IntType})
        ){
            backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?:0
            ProductDetailScreen(productId,navController)
        }
    }
}