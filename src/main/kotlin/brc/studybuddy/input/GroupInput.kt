package brc.studybuddy.input

import brc.studybuddy.model.Group
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GroupInput(
    @JsonProperty(value = "title")
    var title: String? = null,

    @JsonProperty(value = "description")
    var description: String? = null
) : DataInput<Group, GroupInput>
{
    override fun toModel() = Group(
        title = this.title!!,
        description = this.description
    )

    override fun updateModel(model: Group) = Group(
        model.id,
        this.title ?: model.title,
        this.description ?: model.description
    )
}
