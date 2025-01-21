package com.example.furnitureapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnitureapp.model.Cart
import com.example.furnitureapp.model.Order
import com.example.furnitureapp.retrofit.CartRetrofitClient
import com.example.furnitureapp.retrofit.OrderRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    var listOfOrderItem: List<Order> by mutableStateOf(emptyList())
    fun getOrderInfo(userId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = OrderRetrofitClient.orderAPIService.getAllOrder(userId)
                Log.d("CartViewModel", "get Products  successfully: ${response.toString()}")
                response.let{
                    listOfOrderItem = it
                    Log.d("CartViewModel", "Products fetched successfully: ${it.size}")
                }?: Log.e("ProductViewModel", "No products found in the response")


            }catch (e:Exception){
                Log.e("CartViewModel", "Error getting product: ${e.localizedMessage}")
            }
        }
    }

}