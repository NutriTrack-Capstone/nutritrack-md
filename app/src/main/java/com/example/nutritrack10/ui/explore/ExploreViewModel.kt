package com.example.nutritrack10.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.data.remote.model.FoodItem
import com.example.nutritrack10.data.remote.response.ExploreResponse
import com.example.nutritrack10.data.remote.response.GetExploreFoodResponse
import com.example.nutritrack10.data.remote.response.LoginResponse
import kotlinx.coroutines.launch

class ExploreViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _response = MutableLiveData<ExploreResponse>()
    val response: LiveData<ExploreResponse> get() = _response
    fun getExploreData(string: String){
        viewModelScope.launch {
            try {
                val result = userRepository.getExploreFood(string)
                _response.value = result
            }catch (e: Exception) {
                println(e.message)
        }
        }
    }
}