package com.example.nutritrack10.network

import com.example.nutritrack10.data.remote.response.ExploreResponse
import com.example.nutritrack10.data.remote.response.GetDailyNutritionResponse
import com.example.nutritrack10.data.remote.response.GetExploreFoodResponse
import com.example.nutritrack10.data.remote.response.GetRecommendationResponse
import com.example.nutritrack10.data.remote.response.GetUserProfileResponse
import com.example.nutritrack10.data.remote.response.LoginResponse
import com.example.nutritrack10.data.remote.response.RegisterResponse
import com.example.nutritrack10.data.remote.response.ScanResponse
import com.example.nutritrack10.data.remote.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    @POST("register")
//    fun registerUser(@Body user: User): Call<RegisterResponse>
    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("username") name: String,
        @Field("password") password: String
    ): RegisterResponse
//    ): UserResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("username") name: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("user-profile/{username}")
    suspend fun getUserProfile(
    ): GetUserProfileResponse

    @FormUrlEncoded
    @POST("/user-profile")
    suspend fun saveUserProfile(
        @Field("username") username: String,
        @Field("age") age: Int,
        @Field("height") height: Int,
        @Field("weight") weight: Int,
        @Field("gender") gender: Int
    ): UserResponse

    @FormUrlEncoded
    @PUT("/user-profile")
    suspend fun updateUserProfile(
        @Field("username") username: String,
        @Field("age") age: Int,
        @Field("height") height: Int,
        @Field("weight") weight: Int,
        @Field("gender") gender: Int
    ): UserResponse

    @GET("/nutrition-user/{username}")
    suspend fun getDailyNutrition(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): GetDailyNutritionResponse

    @FormUrlEncoded
    @PUT("/nutrition-user")
    suspend fun updateDailyNutrition(
        @Field("username") username: String,
        @Field("calories") calories: Int,
        @Field("carbo") carbo: Int,
        @Field("protein") protein: Int,
        @Field("flat") fat: Int
    ): UserResponse

//    @GET("/food")
//    suspend fun getExploreFood(
//        @Query("name") foodName: String
//    ): GetExploreFoodResponse

    @GET("/food")
    suspend fun getExploreFood(
        @Query("name") foodName: String
    ): ExploreResponse

//    @FormUrlEncoded
//    @PUT("/daily-nutrition")
//    suspend fun resetDailyNutrition(
//    ): ResetAllUserResponse

//    parameter query dalam endpoint
//    @GET("/recommendation")
//    suspend fun getRecommendation(
//        @Query("maintenanceCalories") maintenanceCalories: Int,
//        @Query("calorieLeft") calorieLeft: Int
//    ): GetRecommendationResponse

    @GET("/recommendation")
    suspend fun getRecommendation(
        @Query("maintenanceCalories") maintenanceCalories: Int,
        @Query("calorieLeft") calorieLeft: Int
    ): GetRecommendationResponse

    @Multipart
    @POST("/image-detection")
    suspend fun postImage(
        @Part file: MultipartBody.Part
    ): ScanResponse

//    @Multipart
//    @POST("/image-detection")
//    fun postImage(
//        @Part file: MultipartBody.Part
//    ): Call<ScanResponse>
}
