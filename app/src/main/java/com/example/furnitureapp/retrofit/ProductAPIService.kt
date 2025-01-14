    package com.example.furnitureapp.retrofit

    import com.example.furnitureapp.model.Product
    import retrofit2.http.GET

    interface ProductAPIService {
        @GET("product_api/list_of_products.php")
        suspend fun getAllProduct():List<Product>
    }