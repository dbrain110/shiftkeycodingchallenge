import com.google.gson.annotations.SerializedName

data class Skill(

    @field:SerializedName("color")
    val color: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)