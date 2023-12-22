package com.example.nutritrack10.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.data.remote.response.ScanResponse
import com.example.nutritrack10.network.ApiConfig
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddImageViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _uploadResponse = MutableLiveData<ScanResponse>()
    val uploadResponse: LiveData<ScanResponse> get() = _uploadResponse

    fun uploadImage(imagePart: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = userRepository.postImage(imagePart)
                _uploadResponse.value = response
            } catch (e: Exception) {
                // Handle network errors or other exceptions
                Log.e("UploadResponse", "Exception: ${e.message}")
            }
        }
    }
}
