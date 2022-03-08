package com.shiftkey.codingchallenge.model

import Meta
import com.google.gson.annotations.SerializedName

data class ShiftResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("links")
	val links: List<Any?>? = null
)














