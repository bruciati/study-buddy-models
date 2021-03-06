package brc.studybuddy.input

import brc.studybuddy.model.Meeting
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeetingInput(
    @JsonProperty(value = "group_id")
    @JsonAlias("groupId", "groupID")
    val groupId: Long? = null,

    @JsonProperty(value = "name")
    val name: String? = null,

    @JsonProperty(value = "datetime")
    @JsonAlias("dateTime", "date_time")
    val dateTime: Long? = null,

    @JsonProperty(value = "type")
    val type: Meeting.Type? = null,

    @JsonProperty(value = "location")
    val location: String? = null
) : DataInput<Meeting, MeetingInput>
{
    override fun toModel() = Meeting(
        groupId = this.groupId!!,
        name = this.name!!,
        dateTime = this.dateTime!!,
        type = this.type ?: Meeting.Type.ONLINE,
        location = this.location ?: "unknown"
    )

    override fun updateModel(model: Meeting) = Meeting(
        model.id,
        model.groupId,
        this.name ?: model.name,
        this.dateTime ?: model.dateTime,
        this.type ?: model.type,
        this.location ?: model.location
    )
}
