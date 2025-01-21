package com.example.furnitureapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnitureapp.model.APIResponse
import com.example.furnitureapp.model.Addres
import com.example.furnitureapp.model.Product
import com.example.furnitureapp.model.User
import com.example.furnitureapp.retrofit.UserRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Callback
import retrofit2.Call
import retrofit2.awaitResponse
import kotlin.jvm.Throws

class UserViewModel : ViewModel() {
    var userState: AuthState by mutableStateOf(AuthState.BanDau)
        private set
    var listAddress: List<Addres> by mutableStateOf(emptyList())
    fun loginUser(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            userState = AuthState.Error("Username or password is empty!")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = UserRetrofitClient.userAPIService.checkLoginUser(username, password)
                Log.d("UserViewModel", "API Response: $response")

                if (!response.tai_khoan.isNullOrEmpty() && !response.mat_khau.isNullOrEmpty()) {
                    userState = AuthState.XacThuc(response)
                    Log.d("UserViewModel", "Login successful: ${response.tai_khoan}")
                } else {
                    userState = AuthState.Error("Invalid username or password!")
                    Log.d("UserViewModel", "Login failed: Invalid credentials")
                }
            } catch (e: Exception) {
                userState = AuthState.Error("Error: ${e.localizedMessage}")
                Log.e("UserViewModel", "Login error: ${e.localizedMessage}")
            }
        }
    }

    fun userSignUp(fullname: String, username: String, password: String, callback: (APIResponse) -> Unit) {
        if (fullname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            callback(APIResponse(error = true, message = "Fullname, username, or password is empty!"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = UserRetrofitClient.userAPIService.userSignUp(fullname, username, password)
                withContext(Dispatchers.Main) {
                    if (!response.error) {
                        callback(APIResponse(error = false, message = "Thông báo: ${response.message}"))
                        Log.d("UserViewModel", "Sign up successful: ${response.message}")
                    } else {
                        callback(APIResponse(error = true, message = "Thông báo: ${response.message}"))
                        Log.d("UserViewModel", "Thông báo: ${response.message}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(APIResponse(error = true, message = "Error: ${e.localizedMessage}"))
                }
                Log.e("UserViewModel", "Sign up error: ${e.localizedMessage}")
            }
        }
    }

    fun userChangePassword(userId: Int, password: String, callback: (APIResponse) -> Unit) {
        if ( password.isEmpty()) {
            callback(APIResponse(error = true, message = "password is empty!"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = UserRetrofitClient.userAPIService.userChangePassword(userId, password)
                withContext(Dispatchers.Main) {
                    if (!response.error) {
                        callback(APIResponse(error = false, message = "Thông báo: ${response.message}"))
                        Log.d("UserViewModel", "Thông báo: ${response.message}")
                    } else {
                        callback(APIResponse(error = true, message = "Thông báo: ${response.message}"))
                        Log.d("UserViewModel", "Thông báo: ${response.message}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(APIResponse(error = true, message = "Error: ${e.localizedMessage}"))
                }
                Log.e("UserViewModel", "Thông báo: ${e.localizedMessage}")
            }
        }
    }

    fun getUserAddress(userId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = UserRetrofitClient.userAPIService.getUserAddress(userId)
                Log.d("UserViewModelId", "API Response: $response")
                if(response!= null){
                    listAddress = response
                }
                Log.d("UserViewModelId", "Address by id fetched successfully: ${listAddress.size}")
            }catch (e:Exception){
                Log.e("UserViewModelId", "Error getting address by id: ${e.localizedMessage}")
            }
        }
    }

    fun logoutUser() {
        userState = AuthState.HuyXacThuc
        userState = AuthState.BanDau
        Log.d("UserViewModel", "User logged out")
    }

    fun checkAuthState(): Boolean {
        return when (userState) {
            is AuthState.XacThuc -> true
            is AuthState.HuyXacThuc -> false
            else -> false
        }
    }
}

sealed class AuthState {
    object BanDau : AuthState()     // Đang xử lý
    data class XacThuc(val user: User) : AuthState()  // Đăng nhập thành công
    object HuyXacThuc : AuthState()        // Đăng xuất
    data class Error(val message: String) : AuthState()      // Lỗi
}