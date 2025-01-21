package com.example.furnitureapp.model

import androidx.annotation.DrawableRes
import com.example.furnitureapp.R


data class Product(
    val id: Int,
    val ma_loai: Int,
    val ten_san_pham: String,
    val gia: Double,
    val so_luong: Int,
    val anh_dai_dien: String,
    val mo_ta: String,
    val kich_thuoc: String,
    val chat_lieu: String,
    val ngay_them: String
)


