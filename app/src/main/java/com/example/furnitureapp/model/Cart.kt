package com.example.furnitureapp.model

import okhttp3.Protocol

data class Cart(
    val id :Int = 0,
    val ma_san_pham : Int,
    val user_id :Int,
    val gia_ban : Double,
    val so_luong :Int,
    val tong_tien : Double = 0.0,
    val ngay_tao: String = ""
)


data class CartResponse(
    val protocol: String,
    val code :String,
    val url :String
)