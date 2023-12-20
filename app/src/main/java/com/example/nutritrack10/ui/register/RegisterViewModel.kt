package com.example.nutritrack10.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.data.remote.response.RegisterResponse
import com.example.nutritrack10.data.remote.response.UserResponse
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

//    private val _registerResponse = MutableLiveData<UserResponse>()
//    val registerResponse: LiveData<UserResponse> get() = _registerResponse
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> get() = _registerResponse

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = userRepository.registerUser(username, password)
                _registerResponse.value = result
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}


//    val registrationStatus: MutableLiveData<Result<UserResponse>> = MutableLiveData()
//    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
//    val errorMessage: MutableLiveData<String> = MutableLiveData()
//
//    fun register(name: String,password: String) {
//        viewModelScope.launch {
//            isLoading.postValue(true)
//            try {
//                val response = userRepository.registerUser(name, password)
//                if (response.error == false || response.error == null) {
//                    registrationStatus.postValue(Result.success(response))
//                } else {
//                    val errorBody = response.message ?: "Registration error"
//                    registrationStatus.postValue(Result.failure(Exception(errorBody)))
//                    errorMessage.postValue(errorBody)
//                }
//            } catch (e: HttpException) {
//                val errorBodyString = e.response()?.errorBody()?.string()
//                try {
//                    val jsonObject = JSONObject(errorBodyString)
//                    val extractedMessage = jsonObject.getString("message")
//                    this@RegisterViewModel.errorMessage.postValue(extractedMessage)
//                } catch (ex: Exception) {
//                    this@RegisterViewModel.errorMessage.postValue("Registration error.")
//                }
//                isLoading.postValue(false)
//            } catch (e: Exception) {
//                registrationStatus.postValue(Result.failure(e))
//                isLoading.postValue(false)
//            } finally {
//                isLoading.postValue(false)
//            }
//        }
//    }


//    fun registerUser(username: String, password: String, callback: (UserResponse) -> Unit) {
//        userRepository.registerUser(username, password, object : Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<RegisterResponse>) {
//                val registerResponse = response.body() ?: UserResponse(false, "Invalid response")
//                callback.invoke(registerResponse)
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                val registerResponse = UserResponse(false, t.localizedMessage ?: "An error occurred")
//                callback.invoke(registerResponse)
//            }
//        })
//    }

