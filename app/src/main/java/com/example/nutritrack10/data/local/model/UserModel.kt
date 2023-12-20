package com.example.nutritrack10.data.local.model
data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)