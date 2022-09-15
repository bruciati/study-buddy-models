package brc.studybuddy.input

import brc.studybuddy.model.Group
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GroupInput(
    @JsonProperty(value = "title")
    val title: String? = null,

    @JsonProperty(value = "area_of_interest")
    @JsonAlias("areaOfInterest", "interest")
    val areaOfInterest: String? = null,

    @JsonProperty(value = "description")
    val description: String? = null
) : DataInput<Group, GroupInput>
{
    override fun toModel() = Group(
        title = this.title!!,
        areaOfInterest = this.areaOfInterest ?: "Other",
        description = this.description
    )

    override fun updateModel(model: Group) = Group(
        model.id,
        this.title ?: model.title,
        this.areaOfInterest ?: model.areaOfInterest,
        this.description ?: model.description
    )
}
