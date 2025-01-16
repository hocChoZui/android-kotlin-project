package com.example.furnitureapp.viewmodel

import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnitureapp.model.Gallery
import com.example.furnitureapp.retrofit.GalleryAPIService
import com.example.furnitureapp.retrofit.GalleryRetrofitClient
import com.example.furnitureapp.retrofit.ProductRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    var listOfImage : List<Gallery> by mutableStateOf(emptyList())

    fun getAllImage(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response  = GalleryRetrofitClient.galleryAPIService.getImageByProductId(id)
                Log.d("GalleryViewModelId", "API Response: $response")
                if(response !=null && response.isNotEmpty()){
                listOfImage = response
                }else
                {
                    listOfImage = emptyList()
                    Log.d("GalleryViewModelId", "Product by id : loi")
                }
                Log.d("GalleryViewModelId", "image by id fetched successfully: ${listOfImage.toString()}")

            } catch (e: Exception) {
                Log.e("GalleryViewModelId", "Error getting image by id: ${e.localizedMessage}")
            }
        }
    }
}