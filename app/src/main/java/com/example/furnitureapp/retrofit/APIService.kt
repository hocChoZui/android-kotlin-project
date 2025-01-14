    package com.example.furnitureapp.retrofit

    import com.example.furnitureapp.model.Categories
    import com.example.furnitureapp.model.Product
    import okhttp3.Response
    import retrofit2.http.GET
    import retrofit2.http.POST
    import retrofit2.http.Path
    import retrofit2.http.Query


    interface ProductAPIService {
        @GET("product_api/list_of_products.php/")
        suspend fun getAllProduct():List<Product>

        @GET("product_api/product_by_id.php")
        suspend fun getProductById(
           @Query("id") productId : Int
        ) : Product

        @GET("product_api/product_by_categoryId.php")
        suspend fun getProductByCateId(
            @Query("id") categoryId : Int
        ) : List<Product>
    }

    interface CategoryAPIService {
        @GET("category_api/list_of_category.php/")
        suspend fun getAllCategory():List<Categories>
    }