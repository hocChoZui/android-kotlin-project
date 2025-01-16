package com.example.furnitureapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnitureapp.model.User
import com.example.furnitureapp.retrofit.UserRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var userState :AuthState by mutableStateOf(AuthState.BanDau)
        private set

    fun loginUser(username: String, password: String){
        if (username.isEmpty() || password.isEmpty()) {
            userState = AuthState.Error("Username or password is empty!")
            return
        }
        userState = AuthState.Loading

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = UserRetrofitClient.userAPIService.checkLoginUser(username, password)
                Log.d("UserViewModel", "API Response: $response")

                if (!response.tai_khoan.isNullOrEmpty() &&  !response.mat_khau.isNullOrEmpty()) {
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

    fun logoutUser() {
        userState = AuthState.HuyXacThuc
        userState = AuthState.BanDau
        Log.d("UserViewModel", "User logged out")
    }
}

sealed class AuthState {
    object BanDau : AuthState()
    object Loading : AuthState()                // Đang xử lý
    data class XacThuc(val user: User) : AuthState()  // Đăng nhập thành công
    object HuyXacThuc : AuthState()        // Đăng xuất
    data class Error(val message: String) : AuthState()      // Lỗi
}