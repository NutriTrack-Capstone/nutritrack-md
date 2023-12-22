package com.example.nutritrack10.data.remote


import com.example.nutritrack10.data.local.UserPreferences
import com.example.nutritrack10.data.local.model.UserModel
import com.example.nutritrack10.data.remote.response.ExploreResponse
import com.example.nutritrack10.data.remote.response.GetDailyNutritionResponse
import com.example.nutritrack10.data.remote.response.GetExploreFoodResponse
import com.example.nutritrack10.data.remote.response.GetRecommendationResponse
import com.example.nutritrack10.data.remote.response.LoginResponse
import com.example.nutritrack10.data.remote.response.RegisterResponse
import com.example.nutritrack10.data.remote.response.ScanResponse
import com.example.nutritrack10.data.remote.response.UserResponse
import com.example.nutritrack10.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.Call
import javax.security.auth.callback.Callback

class UserRepository(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) {

//    suspend fun registerUser(username: String, password: String): UserResponse {
//        return apiService.registerUser(username, password)
//    }

    suspend fun registerUser(username: String, password: String): RegisterResponse {
        return apiService.registerUser(username, password)
    }

    suspend fun loginUser(username: String, password: String): LoginResponse {
        val resp = apiService.loginUser(username, password)
        userPreferences.saveSession(username, resp.token?:"", true)
        return resp
    }
    suspend fun getRecommendation(maintenanceCalories: Int, calorieLeft: Int):GetRecommendationResponse {
        return apiService.getRecommendation(maintenanceCalories, calorieLeft)
    }

    suspend fun logout() {
        userPreferences.logout()
    }
//    suspend fun saveAuthToken(token: String) {
//        userPreferences.saveAuthToken(token)
//    }
    suspend fun getAuthToken():String {
        return userPreferences.getAuthToken().first()?:""
    }
    suspend fun getUsername():String {
        return userPreferences.getUsername().first()?:""
    }
    suspend fun getSession(): UserModel{
        return userPreferences.getSession().first()
    }
    suspend fun setCurrentDate(string: String){
        return userPreferences.setCurrentDate(string)
    }
//    suspend fun getExploreFood(foodName: String): GetExploreFoodResponse {
//        return apiService.getExploreFood(foodName)
//    }

    suspend fun getExploreFood(foodName: String): ExploreResponse {
        return apiService.getExploreFood(foodName)
    }
    suspend fun getDailyNutrition(): GetDailyNutritionResponse {
        val res = getAuthToken()
        val res2 = getUsername()
        return apiService.getDailyNutrition(res,res2)
    }
    suspend fun saveProfileDetail(
        toInt: Int,
        toInt1: Int,
        toInt2: Int,
        result: Int
    ):UserResponse{
        return apiService.saveUserProfile(getUsername(), toInt, toInt1, toInt2,result)
    }
    suspend fun updateProfileDetail(
        toInt: Int,
        toInt1: Int,
        toInt2: Int,
        result: Int
    ):UserResponse{
        return apiService.updateUserProfile(getUsername(), toInt, toInt1, toInt2,result)
    }

    suspend fun getCurrentDate():String {
        return userPreferences.getCurrentDate().first()?:""
    }

//    get nutrisi scan
//    suspend fun uploadImage(imagePart: MultipartBody.Part): ScanResponse {
//        return apiService.postImage(imagePart)
//    }

//    suspend fun postImage(imagePart: MultipartBody.Part): ScanResponse {
//        return apiService.postImage(imagePart)
//    }

    suspend fun postImage(file: MultipartBody.Part): ScanResponse {
        return apiService.postImage(file)
    }

//    suspend fun postImage(file: MultipartBody.Part): Call<ScanResponse> {
//        return apiService.postImage(file)
//    }

//    suspend fun postImage(imagePart: MultipartBody.Part): ScanResponse {
//        return withContext(Dispatchers.IO) {
//            apiService.postImage(imagePart)
//        }
//    }

    suspend fun updateDailyNutrition(
        cal:Int, carb:Int, prot:Int, fat:Int
    ): UserResponse {
        return apiService.updateDailyNutrition(
            getUsername(),
            cal,
            carb,
            prot,
            fat
        )
    }

//    suspend fun resetDailyNutrition() {
//        apiService.resetDailyNutrition()
//    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreferences
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }

}
