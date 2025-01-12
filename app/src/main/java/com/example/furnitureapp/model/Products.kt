package com.example.furnitureapp.model

import androidx.annotation.DrawableRes
import com.example.furnitureapp.R

data class TopSellingProduct(
    val id: Int,
    val name: String,
    @DrawableRes val imageResId: Int,
    val price: String,
    )

val topSellingProductList = listOf(
    TopSellingProduct(1,
        "Ghế sofa",
        R.drawable.seater_sofa,
        "15.000.000 đ"
        ),
    TopSellingProduct(2,
        "Giường ngủ ",
        R.drawable.double_bed,
        "18.000.000 đ"
        ),
    TopSellingProduct(3,
        "Bàn làm việc",
        R.drawable.office_desk,
        "12.000.000 đ"
        ),
    TopSellingProduct(4,
        "Test ",
        R.drawable.carts,
        "12.000.000 đ"
        ),
    TopSellingProduct(5,
        "Test 2",
        R.drawable.wardrobe,
        "12.000.000 đ"
        ),
    TopSellingProduct(6,
        "Test 3",
        R.drawable.bookshelf,
        "12.000.000 đ"
        ),
    TopSellingProduct(7,
        "Test 4",
        R.drawable.facebook,
        "12.000.000 đ"
        ),
    TopSellingProduct(8,
        "Test 5",
        R.drawable.discountshape,
        "12.000.000 đ"
        ),
    TopSellingProduct(9,
        "Test 5",
        R.drawable.discountshape,
        "12.000.000 đ"
    ),
    TopSellingProduct(10,
        "Test 5",
        R.drawable.discountshape,
        "12.000.000 đ"
    ),
    TopSellingProduct(11,
        "Test 5",
        R.drawable.discountshape,
        "12.000.000 đ"
    ),
)

