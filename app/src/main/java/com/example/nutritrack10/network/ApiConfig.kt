package com.example.nutritrack10.network


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//object ApiConfig {
//    fun getApiService(): ApiService {
//        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
////            val authInterceptor = Interceptor { chain ->
////            val req = chain.request()
////            val requestHeaders = req.newBuilder()
////                .addHeader("Authorization", "Bearer $token")
////                .build()
////            chain.proceed(requestHeaders)
//        val client = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://carbide-parser-406505.et.r.appspot.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//        return retrofit.create(ApiService::class.java)
//
//        val api: ApiService by lazy {
//            retrofit.create(ApiService::class.java)
//        }
//
//    }
//}

object ApiConfig {

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://carbide-parser-406505.et.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
