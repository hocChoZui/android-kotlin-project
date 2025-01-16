    package com.example.furnitureapp.retrofit

    import com.example.furnitureapp.model.Categories
    import com.example.furnitureapp.model.Gallery
    import com.example.furnitureapp.model.Product
    import com.example.furnitureapp.model.User
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

    interface GalleryAPIService{
        @GET("gallery_api/list_of_image.php")
        suspend fun getImageByProductId(
            @Query("id") productId : Int
        ) : List<Gallery>
    }

    interface UserAPIService{
        @GET("user_api/user_login.php")
        suspend fun checkLoginUser(
            @Query("username") username: String,
            @Query("password") password: String
        ) : User
    }