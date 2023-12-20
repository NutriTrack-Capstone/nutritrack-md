package com.example.nutritrack10.ui.camera

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritrack10.data.remote.ModelRepository
import com.example.nutritrack10.data.remote.response.PredictionResponse
import kotlinx.coroutines.launch

class CameraResultViewModel(private val repository: ModelRepository) : ViewModel() {

    private val _predictionResponse = MutableLiveData<PredictionResponse>()
    val predictionResponse: LiveData<PredictionResponse> get() = _predictionResponse

    fun predictImage(image: Bitmap) {
        viewModelScope.launch {
            val result = repository.predictImage(image)
            _predictionResponse.postValue(result)
        }
    }
}
