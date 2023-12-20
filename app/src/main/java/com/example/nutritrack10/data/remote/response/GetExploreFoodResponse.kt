package com.example.nutritrack10.data.remote.response

import com.example.nutritrack10.data.remote.model.FoodItem
import com.google.gson.annotations.SerializedName

data class GetExploreFoodResponse(

	@field:SerializedName("data")
	val foodList: List<FoodItem>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)
