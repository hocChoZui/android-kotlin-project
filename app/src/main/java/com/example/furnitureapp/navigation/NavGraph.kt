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
import com.example.furnitureapp.view.order.EmptyOrders
import com.example.furnitureapp.view.order.OrderDetailsScreen
import com.example.furnitureapp.view.order.OrderSummaryScreen
import com.example.furnitureapp.view.order.OrdersScreen
import com.example.furnitureapp.view.product.ProductDetailScreen
import com.example.furnitureapp.view.user.ProfileScreen
import com.example.furnitureapp.view.welcome.LoginScreen
import com.example.furnitureapp.view.welcome.RegisterScreen
import com.example.furnitureapp.viewmodel.CategoryViewModel
import com.example.furnitureapp.viewmodel.GalleryViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel
import com.example.furnitureapp.viewmodel.UserViewModel


@Composable
fun FurnitureNavGraph(modifier: Modifier = Modifier,
                     userViewModel: UserViewModel,
                      productViewModel: ProductViewModel,
                      categoryViewModel: CategoryViewModel,
                      galleryViewModel: GalleryViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route

    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(modifier, navController, userViewModel)
        }

        composable(route = Screen.EmptyOrderScreen.route) {
            EmptyOrders(modifier,navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(modifier, navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(modifier,navController,userViewModel,productViewModel,categoryViewModel)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(modifier, navController,userViewModel)
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
        composable(route = Screen.ShowProductByCategoryName.route+ "/{id}/{name}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType } ,
                    navArgument("name") { type = NavType.StringType }
                )
        ) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val name = backStackEntry.arguments?.getString("name") ?: "Không rõ"
            ShowProductByCategory(id, navController, productViewModel, name)
        }
        composable(route = Screen.ProductDetailScreen.route + "/{productId}",
            arguments = listOf(navArgument("productId"){type = NavType.IntType})
        ){backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?:0
            ProductDetailScreen(productId,navController,productViewModel,galleryViewModel)
        }
    }
}