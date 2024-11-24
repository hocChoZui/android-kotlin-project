package com.example.furnitureapp.navigation

sealed class Screen(val route:String){
    object MainScreen:Screen(route = "main_screen")
    object HomeScreen:Screen(route = "home_screen")
    object EmptyOrderScreen: Screen(route = "order_screen")
    object LoginScreen: Screen(route = "login_screen")
    object RegisterScreen: Screen(route = "register_screen")
    object ProfileScreen: Screen(route = "profile_screen")



}