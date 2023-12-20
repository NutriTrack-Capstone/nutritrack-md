package com.example.nutritrack10.data.remote

import android.graphics.Bitmap
import com.example.nutritrack10.data.remote.response.PredictionResponse
import com.example.nutritrack10.network.model.ApiConfig2
import com.example.nutritrack10.network.model.ApiService2
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream


class ModelRepository(private val apiService2: ApiService2) {

    suspend fun predictImage(image: Bitmap): PredictionResponse {
        val requestBody = createRequestBody(image)
        return apiService2.predictImage(ApiConfig2.SIGNATURE_NAME, requestBody)
    }

//    untuk convert jpeg ke x,y,channel
    private fun createRequestBody(image: Bitmap): RequestBody {
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()

        return byteArray.toRequestBody("image/jpeg".toMediaType())
    }

}
