import com.google.gson.annotations.SerializedName
import com.shiftkey.codingchallenge.model.Specialty

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