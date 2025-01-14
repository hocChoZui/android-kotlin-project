package com.example.furnitureapp.viewmodel

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel(){
  private val _auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState :LiveData<AuthState> = _authState

    fun CheckAuthStatus(){
        if(_auth.currentUser == null){
         _authState.value = AuthState.HuyXacThuc
        }else{
            _authState.value = AuthState.XacThuc
        }
    }

    fun Login(email : String, password : String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password is empty!")
            return
        }
        _authState.value = AuthState.Loading
        _auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.XacThuc
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong!")
                }
            }
    }
    fun Register(email : String, password : String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password is empty!")
            return
        }
        _authState.value = AuthState.Loading
        _auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.DangKyThanhCong
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong!")
                }
            }
    }
    fun Logout(){
        _auth.signOut()
        _authState.value = AuthState.HuyXacThuc
    }

}

sealed class AuthState{
    object XacThuc : AuthState()
    object HuyXacThuc : AuthState()
    object Loading : AuthState()
    object DangKyThanhCong : AuthState()
    data class Error(val message : String) : AuthState()

}