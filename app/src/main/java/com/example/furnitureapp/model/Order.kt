package com.example.furnitureapp.model

data class Order(
    val id :Int,
    val  ma_khach_hang: Int,
    val ma_dia_chi :Int,
    val ma_phuong_thuc : Int,
    val ngay_dat :String,
    val tong_tien :Double,
    val trang_thai : String,
    val ma_san_pham :Int,
    val so_luong : Int,
)
