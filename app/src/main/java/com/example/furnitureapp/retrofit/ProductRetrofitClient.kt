package com.example.furnitureapp.retrofit

import androidx.test.espresso.core.internal.deps.dagger.Module
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Constant{
    const val BASE_URL = "http://192.168.180.84/api/"
}

object ProductRetrofitClient{
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val ProductAPIService: ProductAPIService by lazy {
        retrofit.create(ProductAPIService::class.java)
    }
}

//@Module
//@InstallIn(SingletonComponent ::class)
//class NetworkModule {
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://6471a6946a9370d5a41a84bb.mockapi.io/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//}
