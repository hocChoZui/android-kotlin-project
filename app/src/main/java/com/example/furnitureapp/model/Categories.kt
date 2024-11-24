package com.example.furnitureapp.model

import com.example.furnitureapp.R

data class Categories(
    val cateResId: Int,
    val name : String,
)


val categoriesList = listOf(
    Categories(cateResId = R.drawable.bookshelf, name = "Kệ sách",),
    Categories(cateResId = R.drawable.double_bed, name = "Giường ngủ"),
    Categories(cateResId = R.drawable.seater_sofa, name = "Sofa"),
    Categories(cateResId = R.drawable.hairdresser, name = "Bàn trang điểm"),
    Categories(cateResId = R.drawable.television, name = "Kệ TV"),
    Categories(cateResId = R.drawable.office_desk, name = "Bàn làm việc"),
    Categories(cateResId = R.drawable.wardrobe, name = "Tủ giày dép"),

)