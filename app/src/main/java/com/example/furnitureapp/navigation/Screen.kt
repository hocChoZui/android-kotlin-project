package com.example.furnitureapp.navigation

sealed class Screen(val route:String){
    object MainScreen:Screen(route = "main_screen")
    object HomeScreen:Screen(route = "home_screen")
    object EmptyOrderScreen: Screen(route = "order_screen")
    object LoginScreen: Screen(route = "login_screen")
    object RegisterScreen: Screen(route = "register_screen")
    object ProfileScreen: Screen(route = "profile_screen")
    object EmptyOrders: Screen (route = "empty_orders")
    object Order : Screen (route = "order")
    object OrderSummary : Screen (route = "order_summary")
    object OrderDetailScreen : Screen (route = "order_detail")
    object EmptyCart : Screen (route = "empty_cart")
    object Cart : Screen (route = "cart")
    object ShowProductByCategoryName : Screen (route = "show_product_by_category")
    object ProductDetailScreen : Screen(route = "show_product_by_id")
    object EditProfileScreen : Screen(route = "edit_profile")
    object ChangePasswordScreen : Screen(route = "change_password")

}