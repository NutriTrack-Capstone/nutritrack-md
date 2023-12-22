package com.example.nutritrack10.ui.scanresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nutritrack10.data.remote.UserRepository

class CameraResultViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CameraResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CameraResultViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}