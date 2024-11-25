package com.example.furnitureapp.model

import androidx.annotation.DrawableRes
import com.example.furnitureapp.R

data class Banner(
    val id : Int,
    @DrawableRes val imageResId : Int,
)

val bannerList = listOf(
    Banner(1, R.drawable.banner_1),
    Banner(2, R.drawable.banner_2),
)