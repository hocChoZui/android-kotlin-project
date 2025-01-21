    package com.example.furnitureapp.retrofit

    import com.example.furnitureapp.model.APIResponse
    import com.example.furnitureapp.model.Addres
    import com.example.furnitureapp.model.Cart
    import com.example.furnitureapp.model.Categories
    import com.example.furnitureapp.model.Gallery
    import com.example.furnitureapp.model.Order
    import com.example.furnitureapp.model.Product
    import com.example.furnitureapp.model.User
    import okhttp3.Response
    import okhttp3.ResponseBody
    import retrofit2.Call
    import retrofit2.http.Body
    import retrofit2.http.DELETE
    import retrofit2.http.Field
    import retrofit2.http.FormUrlEncoded
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
            @Query("tai_khoan") username: String,
            @Query("mat_khau") password: String
        ) : User

        @FormUrlEncoded
        @POST("user_api/user_signup.php")
        suspend fun userSignUp(
            @Field("ten_khach_hang") fullname :String,
            @Field("tai_khoan") username :String,
            @Field("mat_khau") password: String
        ) : APIResponse

        @GET("user_api/get_user_address.php")
        suspend fun  getUserAddress(
            @Query("id") userId :Int
        ) : List<Addres>

        @FormUrlEncoded
        @POST("user_api/user_change_password.php")
        suspend fun userChangePassword(
            @Field("id") userId: Int,
            @Field("mat_khau") newpass :String
        ) :APIResponse
    }


    interface  CartAPIService{
        @GET("cart_api/get_cart.php")
        suspend fun getCartInfo(
            @Query("user_id") userId : Int
        ) : List<Cart>

        @POST("cart_api/update_cart.php")
        suspend fun addToCart(
            @Body cart: Cart
//            @Field("user_id") userId: Int,
//        @Field("ma_san_pham") productId: Int
        ): ResponseBody

        @DELETE("cart_api/delete_cart.php")
        suspend fun removeCartItem(
            @Query("user_id") userId : Int,
            @Query("ma_san_pham") productId: Int
        ) : ResponseBody

    }

    interface  OrderAPIService{
        @GET("order_api/list_of_order.php")
        suspend fun getAllOrder(
            @Query("user_id") userId: Int
        ) : List<Order>
    }