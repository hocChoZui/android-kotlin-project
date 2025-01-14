package com.example.furnitureapp.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Constant{
    const val BASE_URL = "http://192.168.101.59/api/"
}

object ProductRetrofitClient{
     val productAPIService : ProductAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory
                .create(GsonBuilder().create()))
            .build()
            .create(ProductAPIService::class.java)
    }

}

object CategoryRetrofitClient {
    val categoryAPIService: CategoryAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CategoryAPIService::class.java)
    }
}
