package com.example.nutritrack10.ui.overview

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.data.remote.response.GetDailyNutritionResponse
import com.example.nutritrack10.data.remote.response.GetRecommendationResponse
import com.example.nutritrack10.data.remote.response.LoginResponse
import com.example.nutritrack10.data.remote.response.Recomendation1
import kotlinx.coroutines.launch

class OverviewViewModel(private val userRepository: UserRepository) : ViewModel() {

//    menampilkan daily nutrition
    private val _response = MutableLiveData<GetDailyNutritionResponse>()
    val response: LiveData<GetDailyNutritionResponse> get() = funtion()

//    tanggal sekarang
    private val _currentDate = MutableLiveData<String>()
    val currentDate:LiveData<String> get() = getCurrentDates()

//    captured image
    private val _capturedImage = MutableLiveData<Bitmap?>()
    val capturedImage: LiveData<Bitmap?> get() = _capturedImage

//    recommendation
//    private val _recommmendation1 = MutableLiveData<GetRecommendationResponse>()
//    val recomendation1: LiveData<GetRecommendationResponse> get() = _recommmendation1

//    recommendation
    private val _recommendationData = MutableLiveData<GetRecommendationResponse>()
    val recommendationData: LiveData<GetRecommendationResponse> get() = _recommendationData


    //    menyimpan login
    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = userRepository.loginUser(username, password)
                _loginResponse.value = result
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    fun fetchRecommendationData(maintenanceCalories: Int, calorieLeft: Int) {
        viewModelScope.launch {
            try {
                val recommendationResponse = userRepository.getRecommendation(maintenanceCalories, calorieLeft)
                _recommendationData.postValue(recommendationResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


//    fun getRecommendation1(maintenanceCalories: Int, calorieLeft: Int): LiveData<RecomendationData>{
//        viewModelScope.launch {
//            userRepository.getRecommendation(maintenanceCalories, calorieLeft)
//        }
//        return _recommmendationData
//    }

//    err
//    fun funtion(): LiveData<GetDailyNutritionResponse>{
//        viewModelScope.launch {
//            _response.postValue(userRepository.getDailyNutrition())
//        }
//
//        return _response
//    }

    fun funtion(): LiveData<GetDailyNutritionResponse> {
        viewModelScope.launch {
            try {
                _response.postValue(userRepository.getDailyNutrition())
            } catch (e: Exception) {
                // Handle error
                Log.e("OverviewViewModel", "Error during funtion: ${e.message}")
            }
        }
        return _response
    }

//
//    fun getRecommendation2(maintenanceCalories: Int, calorieLeft: Int): LiveData<GetRecommendationResponse>{
//        viewModelScope.launch {
//            _recommmendation1.postValue(userRepository.getRecommendation(maintenanceCalories, calorieLeft))
//        }
//        return _recommmendation1
//    }
//    recommendation

    fun setCurrentDate(string: String) {
        viewModelScope.launch {
            userRepository.setCurrentDate(string)
        }
    }

//    salah. ini hanya akan memberikan masukan 0 pada semua nutrisi user.
//    bukan menjadikan dailyCaloriesLeft, dll. user menjadi 0 di hari baru
    fun resetDailyNutrition() {
        viewModelScope.launch {
            try {
                userRepository.updateDailyNutrition(0, 0, 0, 0)
                funtion()
            } catch (e: Exception) {
                // Handle error
                Log.e("OverviewViewModel", "Error during resetDailyNutrition: ${e.message}")
                // Add additional handling as needed
            }
        }
    }

    fun getCurrentDates():LiveData<String> {
        viewModelScope.launch {
            _currentDate.postValue(userRepository.getCurrentDate())
        }
        return _currentDate
    }

    fun processCapturedImage(image: Bitmap?) {
        if (image != null) {
            _capturedImage.value = image
        } else {
            Log.e("OverviewViewModel", "Received null image in processCapturedImage")
        }
    }
}