package com.example.nutritrack10

import android.content.Context
import com.example.nutritrack10.data.local.UserPreferences
import com.example.nutritrack10.data.local.dataStore
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.network.ApiConfig

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val apiService = ApiConfig.api
        val userPreference = UserPreferences.getInstance(context.dataStore)
        return UserRepository.getInstance(apiService, userPreference)
    }

}