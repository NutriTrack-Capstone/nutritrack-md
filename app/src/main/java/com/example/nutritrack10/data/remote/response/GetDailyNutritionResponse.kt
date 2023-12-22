package com.example.nutritrack10.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetDailyNutritionResponse(

	@field:SerializedName("data")
	val data: DailyNutrition? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

//	dihapus
//	@field:SerializedName("message")
//	val message: String? = ""
)

data class DailyNutrition(

	@field:SerializedName("currentCalories")
	val currentCalories: Int? = null,

	@field:SerializedName("maintainProtein")
	val maintainProtein: Int? = null,

	@field:SerializedName("maintainCalories")
	val maintainCalories: Int? = null,

	@field:SerializedName("currentCarbo")
	val currentCarbo: Int? = null,

	@field:SerializedName("maintainFat")
	val maintainFat: Int? = null,

	@field:SerializedName("dailyCaloriesLeft")
	val dailyCaloriesLeft: Int? = null,

	@field:SerializedName("currentFat")
	val currentFat: Int? = null,

	@field:SerializedName("dailyProteinLeft")
	val dailyProteinLeft: Int? = null,

	@field:SerializedName("currentProtein")
	val currentProtein: Int? = null,

	@field:SerializedName("dailyCarboLeft")
	val dailyCarboLeft: Int? = null,

	@field:SerializedName("maintainCarbo")
	val maintainCarbo: Int? = null,

	@field:SerializedName("dailyFatLeft")
	val dailyFatLeft: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
)
