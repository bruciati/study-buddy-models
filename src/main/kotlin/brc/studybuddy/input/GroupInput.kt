package brc.studybuddy.input

import brc.studybuddy.model.Group
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GroupInput(
    @JsonProperty(value = "id")
    val id: Long? = null,

    @JsonProperty(value = "title")
    val title: String? = null,

    @JsonProperty(value = "description")
    val description: String? = null
) : DataInput<Group, GroupInput>
{
    override fun toModel() = Group(
        this.id ?: 0,
        this.title!!,
        this.description
    )

    override fun updateModel(model: Group) = Group(
        model.id,
        this.title ?: model.title,
        this.description ?: model.description
    )
}