package com.example.nutritrack10.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nutritrack10.Injection
import com.example.nutritrack10.data.remote.UserRepository

class LoginViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    companion object {
        @Volatile
        private var INSTANCE: LoginViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): LoginViewModelFactory {
            if (INSTANCE == null) {
                synchronized(LoginViewModelFactory::class.java) {
                    INSTANCE = LoginViewModelFactory(Injection.provideUserRepository(context))
                }
            }
            return INSTANCE!!
        }
    }
}
