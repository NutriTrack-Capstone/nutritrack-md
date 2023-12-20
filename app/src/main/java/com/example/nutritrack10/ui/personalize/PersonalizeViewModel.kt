package com.example.nutritrack10.ui.personalize

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.data.remote.response.LoginResponse
import kotlinx.coroutines.launch

class PersonalizeViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _responseSave = MutableLiveData<LoginResponse>()
    val responseSave: LiveData<LoginResponse> get() = _responseSave

    // Fungsi untuk logout
    fun logoutUser() {
        viewModelScope.launch {
            try {
                // Lakukan logout dengan menghapus data sesi pengguna
                userRepository.logout()
            } catch (e: Exception) {
                // Handle error jika diperlukan
                e.printStackTrace()
            }
        }
    }
    fun addProfileData(toInt: Int, toInt1: Int, toInt2: Int, result: Int) {
        viewModelScope.launch {
            try {
                userRepository.saveProfileDetail(toInt, toInt1, toInt2, result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun updateProfileData(toInt: Int, toInt1: Int, toInt2: Int, result: Int) {
        viewModelScope.launch {
            try {
                userRepository.updateProfileDetail(toInt, toInt1, toInt2, result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

