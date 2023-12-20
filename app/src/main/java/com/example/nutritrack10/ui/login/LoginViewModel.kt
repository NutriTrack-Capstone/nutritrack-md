package com.example.nutritrack10.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.data.remote.response.LoginResponse
import com.example.nutritrack10.data.remote.response.UserResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = userRepository.loginUser(username, password)
                _loginResponse.value = result
            } catch (e: Exception) {
                // Handle error
                Log.e("OverviewViewModel", "Error during login: ${e.message}")
            }
        }
    }
    suspend fun isLoggedIn():Boolean{
        return try {
            userRepository.getSession().token != ""
        }catch (e: Exception) {
            false
        }

    }
}
