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
    var listProductByCategoryId : List<Product> by mutableStateOf(emptyList())
    var product : Product by mutableStateOf(Product(0,0,"",0.0,0,"","","","",""))

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

    suspend fun getProductById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response  = ProductRetrofitClient.productAPIService.getProductById(id)
                Log.d("ProductViewModelId", "API Response: $response")
            if(response !=null){
                product = response
            }else
            {
                Log.d("ProductViewModelId", "Product by id : loi")
            }
                Log.d("ProductViewModelId", "Product by id fetched successfully: ${product.toString()}")

            } catch (e: Exception) {
                Log.e("ProductViewModelId", "Error getting product by id: ${e.localizedMessage}")
            }
        }
    }

    fun getProductByCategoryId(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ProductRetrofitClient.productAPIService.getProductByCateId(id)
                Log.d("ProductViewModelCate", "Products fetched successfully: ${response.toString()}")

                response.let {
                    listProductByCategoryId = it
                    Log.d("ProductViewModelCate", "Products fetched successfully: ${it.size}")
                } ?: Log.e("ProductViewModel", "No products found in the response")
            } catch (e: Exception) {
                Log.e("ProductViewModelCate", "Error getting product: ${e.localizedMessage}")
            }
        }
    }
}
