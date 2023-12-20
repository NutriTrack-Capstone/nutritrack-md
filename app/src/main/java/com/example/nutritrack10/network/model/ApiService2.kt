package com.example.nutritrack10.network.model

import com.example.nutritrack10.data.remote.response.PredictionResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService2 {

    @POST("v1/models/nutritrack:predict")
    suspend fun predictImage(
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: RequestBody
    ): PredictionResponse
}
