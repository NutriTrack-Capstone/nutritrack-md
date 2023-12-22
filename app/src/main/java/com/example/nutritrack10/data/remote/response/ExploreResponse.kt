package com.example.nutritrack10.data.remote.response

import com.google.gson.annotations.SerializedName

data class ExploreResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("carbo")
	val carbo: Int? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("protein")
	val protein: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fat")
	val fat: Int? = null,

	@field:SerializedName("calories")
	val calories: Int? = null
)
