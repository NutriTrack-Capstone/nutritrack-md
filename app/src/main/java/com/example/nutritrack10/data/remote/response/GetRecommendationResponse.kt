package com.example.nutritrack10.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetRecommendationResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Recomendation1(

	@field:SerializedName("carbo")
	val carbo: Int? = null,

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("protein")
	val protein: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fat")
	val fat: Int? = null,

	@field:SerializedName("calories")
	val calories: Int? = null
)

data class Recomendation2(

	@field:SerializedName("carbo")
	val carbo: Int? = null,

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("protein")
	val protein: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fat")
	val fat: Int? = null,

	@field:SerializedName("calories")
	val calories: Int? = null
)

data class Recomendation3(

	@field:SerializedName("carbo")
	val carbo: Int? = null,

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("protein")
	val protein: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fat")
	val fat: Int? = null,

	@field:SerializedName("calories")
	val calories: Int? = null
)

data class Data(

	@field:SerializedName("recomendation1")
	val recomendation1: Recomendation1? = null,

	@field:SerializedName("recomendation3")
	val recomendation3: Recomendation3? = null,

	@field:SerializedName("recomendation2")
	val recomendation2: Recomendation2? = null
)
