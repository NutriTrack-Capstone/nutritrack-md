package com.example.nutritrack10.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ScanResponse(

	@field:SerializedName("data")
	val data: HasilScan? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class HasilScan(

	@field:SerializedName("score")
	val score: Float? = null,

	@field:SerializedName("carbo")
	val carbo: Int? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("foodName")
	val foodName: String? = null,

	@field:SerializedName("protein")
	val protein: Int? = null,

	@field:SerializedName("fat")
	val fat: Int? = null,

	@field:SerializedName("calories")
	val calories: Int? = null
)
