package com.rayhdf.sugarcareapp.data.model

import com.google.gson.annotations.SerializedName

data class GetPredictionsResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("predictions")
	val predictions: List<PredictionsItem?>? = null
)

data class Prediction(

	@field:SerializedName("probability")
	val probability: Float? = null,

	@field:SerializedName("label")
	val label: String? = null
)

data class Input(

	@field:SerializedName("bloodPressure")
	val bloodPressure: Int? = null,

	@field:SerializedName("pulmonaryFunction")
	val pulmonaryFunction: Any? = null,

	@field:SerializedName("waistCircumference")
	val waistCircumference: Int? = null,

	@field:SerializedName("insulinLevels")
	val insulinLevels: Any? = null,

	@field:SerializedName("bloodGlucoseLevels")
	val bloodGlucoseLevels: Any? = null,

	@field:SerializedName("cholesterolLevels")
	val cholesterolLevels: Any? = null,

	@field:SerializedName("digestiveEnzymeLevels")
	val digestiveEnzymeLevels: Any? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("weightGainDuringPregnancy")
	val weightGainDuringPregnancy: Int? = null,

	@field:SerializedName("bmi")
	val bmi: Int? = null
)

data class Data(

	@field:SerializedName("input")
	val input: Input? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("prediction")
	val prediction: Prediction? = null
)

data class PredictionsItem(

	@field:SerializedName("predictId")
	val predictId: String? = null,

	@field:SerializedName("data")
	val data: Data? = null
)
