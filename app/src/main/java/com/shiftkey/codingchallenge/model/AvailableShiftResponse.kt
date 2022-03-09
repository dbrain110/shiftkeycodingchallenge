package com.shiftkey.codingchallenge.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableShiftResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("links")
	val links: List<Any?>? = null
)

data class Meta(

	@field:SerializedName("lng")
	val lng: Int? = null,

	@field:SerializedName("lat")
	val lat: Int? = null
)

data class Skill(

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class LocalizedSpecialty(

	@field:SerializedName("specialty")
	val specialty: Specialty? = null,

	@field:SerializedName("specialty_id")
	val specialtyId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state_id")
	val stateId: Int? = null,

	@field:SerializedName("abbreviation")
	val abbreviation: String? = null
)

data class ShiftsItem(

	@field:SerializedName("localized_specialty")
	val localizedSpecialty: LocalizedSpecialty? = null,

	@field:SerializedName("covid")
	val covid: Boolean? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("end_time")
	val endTime: String? = null,

	@field:SerializedName("normalized_start_date_time")
	val normalizedStartDateTime: String? = null,

	@field:SerializedName("shift_kind")
	val shiftKind: String? = null,

	@field:SerializedName("start_time")
	val startTime: String? = null,

	@field:SerializedName("shift_id")
	val shiftId: Int? = null,

	@field:SerializedName("skill")
	val skill: Skill? = null,

	@field:SerializedName("premium_rate")
	val premiumRate: Boolean? = null,

	@field:SerializedName("within_distance")
	val withinDistance: Int? = null,

	@field:SerializedName("facility_type")
	val facilityType: FacilityType? = null,

	@field:SerializedName("normalized_end_date_time")
	val normalizedEndDateTime: String? = null
)

data class Specialty(

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("abbreviation")
	val abbreviation: String? = null
)

data class FacilityType(

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class DataItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("shifts")
	val shifts: List<ShiftsItem?>? = null
)
