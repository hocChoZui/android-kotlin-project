package com.example.furnitureapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnitureapp.model.Categories
import com.example.furnitureapp.model.Product
import com.example.furnitureapp.retrofit.CategoryRetrofitClient
import com.example.furnitureapp.retrofit.ProductRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel(){
    var listOfCategories : List<Categories> by mutableStateOf(emptyList())


    fun getAllCategory(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = CategoryRetrofitClient.categoryAPIService.getAllCategory()
                Log.d("CategoryViewModel", "Cate fetched successfully: ${response.toString()}")
                response.let{
                    listOfCategories = it
                }
            }catch (e: Exception){
                Log.e("CategoryViewModel", "Error getting cate: ${e.localizedMessage}")
            }
        }
    }
}
