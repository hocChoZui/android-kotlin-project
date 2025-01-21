package com.example.furnitureapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnitureapp.model.Cart
import com.example.furnitureapp.retrofit.CartRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    var listOfProductItems: List<Cart> by mutableStateOf(emptyList())
    var cartDelResult by mutableStateOf("")
    var cart : Cart by   mutableStateOf(Cart(0,0,0,0.0,0,0.0,""))
    fun getCartInfo(userId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = CartRetrofitClient.cartAPIService.getCartInfo(userId)
                Log.d("CartViewModel", "get Products  successfully: ${response.toString()}")
                response.let{
                    listOfProductItems = it
                    Log.d("CartViewModel", "Products fetched successfully: ${it.size}")
                }?: Log.e("ProductViewModel", "No products found in the response")


            }catch (e:Exception){
                Log.e("CartViewModel", "Error getting product: ${e.localizedMessage}")
            }
        }
    }

    fun addCart(cart: Cart){
        viewModelScope.launch(Dispatchers.IO) {
            try{
               val response  = CartRetrofitClient.cartAPIService.addToCart(cart)
                Log.d("AddCartViewModel", "new cart  successfully: ${cartDelResult.toString()}")

            }catch (e:Exception){
                Log.e("AddCartViewModel", "Error getting product: ${e.localizedMessage}")
            }
        }
    }

    fun deleteProduct(userId: Int,productId:Int){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response  = CartRetrofitClient.cartAPIService.removeCartItem(userId,productId)
                response.let {
                    cartDelResult = response.toString();
                    getCartInfo(userId)
                }
                Log.d("DelCartViewModel", "da xoa : ${cartDelResult.toString()}")

            }catch (e:Exception){
                Log.e("DelCartViewModel", "Error getting product: ${e.localizedMessage}")
            }
        }
    }

}