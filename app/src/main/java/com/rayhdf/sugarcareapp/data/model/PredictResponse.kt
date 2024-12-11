package com.rayhdf.sugarcareapp.data.model

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("time_request")
	val timeRequest: Int? = null,

	@field:SerializedName("version")
	val version: String? = null
)

data class Result(

	@field:SerializedName("probability")
	val probability: Any? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
