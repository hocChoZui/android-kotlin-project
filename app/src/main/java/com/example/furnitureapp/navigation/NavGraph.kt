package com.example.furnitureapp.navigation

import EditProfileScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.furnitureapp.MainScreen
import com.example.furnitureapp.view.cart.CartScreen
import com.example.furnitureapp.view.categories.ShowProductByCategory
import com.example.furnitureapp.view.order.OrderDetailScreen
import com.example.furnitureapp.view.order.OrderSummaryScreen
import com.example.furnitureapp.view.order.OrdersScreen
import com.example.furnitureapp.view.product.ProductDetailScreen
import com.example.furnitureapp.view.user.ProfileScreen
import com.example.furnitureapp.view.user.ChangePasswordScreen
import com.example.furnitureapp.view.welcome.LoginScreen
import com.example.furnitureapp.view.welcome.RegisterScreen
import com.example.furnitureapp.viewmodel.CartViewModel
import com.example.furnitureapp.viewmodel.CategoryViewModel
import com.example.furnitureapp.viewmodel.GalleryViewModel
import com.example.furnitureapp.viewmodel.OrderViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel
import com.example.furnitureapp.viewmodel.UserViewModel


@Composable
fun FurnitureNavGraph(modifier: Modifier = Modifier,
                     userViewModel: UserViewModel,
                      productViewModel: ProductViewModel,
                      categoryViewModel: CategoryViewModel,
                      galleryViewModel: GalleryViewModel,
                      cartViewModel: CartViewModel,
                      orderViewModel: OrderViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route

    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(modifier, navController, userViewModel)
        }

        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(modifier, navController,userViewModel)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(modifier,navController,userViewModel,productViewModel,categoryViewModel,cartViewModel)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(modifier, navController,userViewModel)
        }
        composable(route = Screen.Order.route + "/{userId}",
            arguments = listOf(
                navArgument("userId") {type = NavType.IntType}
            )
        ) { backStackEntry->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            OrdersScreen(userId,modifier,navController,orderViewModel)
        }
        //
        composable(route = Screen.EditProfileScreen.route + "/{userId}",
            arguments = listOf(
                navArgument("userId") {type = NavType.IntType}
            )
        ) { backStackEntry->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            EditProfileScreen(userId,navController,userViewModel)
        }
            //
        composable(route = Screen.ChangePasswordScreen.route + "/{userId}",
            arguments = listOf(
                navArgument("userId") {type = NavType.IntType}
            )
        ) { backStackEntry->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            ChangePasswordScreen(userId,navController,userViewModel)
        }
        composable(route = Screen.Cart.route + "/{userId}",
            arguments = listOf(
                navArgument("userId") {type = NavType.IntType}
            )
        ) {backStackEntry->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            CartScreen(userId,modifier,navController,cartViewModel,productViewModel)
        }
        composable(route = Screen.OrderSummary.route) {
            OrderSummaryScreen(modifier,navController)
        }
        composable(route = Screen.OrderDetailScreen.route + "/{userId}/{orderId}",
            arguments = listOf(
                navArgument("userId") {type = NavType.IntType},
                navArgument("orderId") { type = NavType.IntType } ,
            )
        ) {backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            val orderId = backStackEntry.arguments?.getInt("orderId") ?: 0
            OrderDetailScreen(userId,orderId,modifier,navController,productViewModel,orderViewModel)
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
            ProductDetailScreen(productId,navController,productViewModel,galleryViewModel,cartViewModel,userViewModel)
        }
    }
}