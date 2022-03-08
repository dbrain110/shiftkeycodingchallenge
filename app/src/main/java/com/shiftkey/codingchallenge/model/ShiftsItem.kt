import com.google.gson.annotations.SerializedName
import com.shiftkey.codingchallenge.model.FacilityType

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
    val withinDistance: Any? = null,

    @field:SerializedName("facility_type")
    val facilityType: FacilityType? = null,

    @field:SerializedName("normalized_end_date_time")
    val normalizedEndDateTime: String? = null
)