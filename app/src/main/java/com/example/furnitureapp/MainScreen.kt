package com.example.furnitureapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.furnitureapp.components.BottomNavBar
import com.example.furnitureapp.model.NavBarItem
import com.example.furnitureapp.view.cart.EmptyCart
import com.example.furnitureapp.view.home.HomeScreen
import com.example.furnitureapp.view.order.EmptyOrders
import com.example.furnitureapp.view.order.OrdersScreen
import com.example.furnitureapp.view.user.ProfileScreen
import com.example.furnitureapp.viewmodel.AuthState
import com.example.furnitureapp.viewmodel.CartViewModel
import com.example.furnitureapp.viewmodel.CategoryViewModel
import com.example.furnitureapp.viewmodel.ProductViewModel
import com.example.furnitureapp.viewmodel.UserViewModel


@Composable
fun MainScreen( modifier: Modifier = Modifier,
                navController: NavController,
                userViewModel: UserViewModel,
                productViewModel: ProductViewModel,
                categoryViewModel: CategoryViewModel,
                cartViewModel: CartViewModel
){


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
            selectedIndex ,
            userViewModel = userViewModel,
            productViewModel = productViewModel,
            categoryViewModel = categoryViewModel,
            cartViewModel = cartViewModel,

            )
    }
}

@Composable
fun ContentScreen(navController: NavController,
                  modifier: Modifier = Modifier,
                  selectedIndex: Int ,
                  userViewModel: UserViewModel,
                  productViewModel: ProductViewModel,
                  categoryViewModel: CategoryViewModel,
                  cartViewModel: CartViewModel
) {
    val state by userViewModel::userState

    when(selectedIndex){
        0-> HomeScreen(modifier,navController,productViewModel,categoryViewModel,userViewModel)
        1-> {
            LaunchedEffect(state) {
                when(state){
                    is AuthState.XacThuc -> {
                        val userId = (state as AuthState.XacThuc).user.id
                        navController.navigate("cart/$userId"){
                            launchSingleTop = true
                        }
                    }
                    is AuthState.HuyXacThuc, is AuthState.BanDau->{
                        navController.navigate("login_screen")
                    }
                    else->{
                        AuthState.Error("Loi chuyen trang")
                    }

                }

            }
        }
        2->{
            LaunchedEffect(state) {
                when(state){
                    is AuthState.XacThuc -> {
                        val userId = (state as AuthState.XacThuc).user.id
                        navController.navigate("order/$userId"){
                            launchSingleTop = true
                        }
                    }
                    is AuthState.HuyXacThuc, is AuthState.BanDau->{
                        navController.navigate("login_screen")
                    }
                    else->{
                        AuthState.Error("Loi chuyen trang")
                    }

                }

            }
        }
        4-> ProfileScreen(modifier,navController,userViewModel)
    }
}
