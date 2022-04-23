package brc.studybuddy.input

import brc.studybuddy.model.MeetingAttendee
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeetingAttendeeInput(
    @JsonProperty(value = "meeting_id", defaultValue = "0")
    @JsonAlias("meetingId", "meetingID")
    val meetingId: Long? = null,

    @JsonProperty(value = "user_id", defaultValue = "0")
    @JsonAlias("userId", "userID")
    val userId: Long? = null,

    @JsonProperty(value = "is_host", defaultValue = "false")
    @JsonAlias("isHost")
    val isHost: Boolean? = null
) : DataInput<MeetingAttendee, MeetingAttendeeInput>
{
    override fun toModel() = MeetingAttendee(
        this.meetingId!!,
        this.userId!!,
        this.isHost ?: false
    )

    override fun updateModel(model: MeetingAttendee) = MeetingAttendee(
        model.meetingId,
        model.userId,
        this.isHost ?: model.isHost
    )
}
