package com.example.nutritrack10.ui.scan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nutritrack10.Injection
import com.example.nutritrack10.data.remote.UserRepository
import kotlin.reflect.KParameter

//class AddImageViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(AddImageViewModel::class.java)) {
//            AddImageViewModel(userRepository) as T
//        } else {
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
//}


class AddImageViewModelFactory private constructor(
    private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddImageViewModel::class.java) -> {
                AddImageViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AddImageViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): AddImageViewModelFactory {
            if (INSTANCE == null) {
                synchronized(AddImageViewModelFactory::class.java) {
                    INSTANCE = AddImageViewModelFactory(
                        Injection.provideUserRepository(context)
                    )
                }
            }
            return INSTANCE!!
        }
    }
}
