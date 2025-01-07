package com.example.furnitureapp.model

import com.example.furnitureapp.R

data class NavBarItem (
    val title: String,
    val iconResId: Int
)

val navBarItemLists = listOf(
    NavBarItem("Home", iconResId = R.drawable.house_blank),
    NavBarItem("Order", iconResId = R.drawable.receipt),
    NavBarItem("Cart", iconResId = R.drawable.shopping_cart),
    NavBarItem("Notification", iconResId = R.drawable.bell),
    NavBarItem("Profile", iconResId = R.drawable.user),
)