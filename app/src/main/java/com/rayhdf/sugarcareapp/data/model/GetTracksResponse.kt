package com.rayhdf.sugarcareapp.data.model

import com.google.gson.annotations.SerializedName

data class GetTracksResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("tracking")
	val tracking: List<TrackingItem?>? = null
)

data class TrackData(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("sugarIntake")
	val sugarIntake: Int? = null,

	@field:SerializedName("bodyWeight")
	val bodyWeight: Int? = null
)

data class TrackingItem(

	@field:SerializedName("data")
	val data: TrackData? = null,

	@field:SerializedName("trackingId")
	val trackingId: String? = null
)
