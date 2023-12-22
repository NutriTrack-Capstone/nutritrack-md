package com.example.nutritrack10.ui.scanresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.data.remote.response.UserResponse
import kotlinx.coroutines.launch

class CameraResultViewModel(private val repository: UserRepository) : ViewModel() {

    private val _predictionResponse = MutableLiveData<UserResponse>()
    val predictionResponse: LiveData<UserResponse> get() = _predictionResponse

    fun updateNutrition( cal:Int, carb:Int, prot:Int, fat:Int) {
        viewModelScope.launch {
            try {
                val result = repository.updateDailyNutrition(cal,carb,prot,fat)
                _predictionResponse.value = result
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
