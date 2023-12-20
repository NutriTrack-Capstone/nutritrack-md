package com.example.nutritrack10.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetUserProfileResponse(

	@field:SerializedName("data")
	val data: UserProfile? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class UserProfile(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("maintainProtein")
	val maintainProtein: Int? = null,

	@field:SerializedName("maintainCalories")
	val maintainCalories: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("maintainFat")
	val maintainFat: Int? = null,

	@field:SerializedName("dailyCaloriesLeft")
	val dailyCaloriesLeft: Int? = null,

	@field:SerializedName("dailyProteinLeft")
	val dailyProteinLeft: Int? = null,

	@field:SerializedName("dailyCarboLeft")
	val dailyCarboLeft: Int? = null,

	@field:SerializedName("maintainCarbo")
	val maintainCarbo: Int? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("dailyFatLeft")
	val dailyFatLeft: Int? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
