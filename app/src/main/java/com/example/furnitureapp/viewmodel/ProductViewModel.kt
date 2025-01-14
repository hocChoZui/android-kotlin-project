package com.example.furnitureapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnitureapp.model.Product
import com.example.furnitureapp.retrofit.ProductRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var listProduct: List<Product> by mutableStateOf(emptyList())

    fun getAllProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ProductRetrofitClient.productAPIService.getAllProduct()
                Log.d("ProductViewModel", "Products fetched successfully: ${response.toString()}")

                response.let {
                    listProduct = it
                    Log.d("ProductViewModel", "Products fetched successfully: ${it.size}")
                } ?: Log.e("ProductViewModel", "No products found in the response")
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error getting product: ${e.localizedMessage}")
            }
        }
    }
}
