package brc.studybuddy.model

import brc.studybuddy.input.MeetingAttendeeInput
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("attendees")
data class MeetingAttendee(
    @Column("meeting_id")
    @JsonProperty(value = "meeting_id", defaultValue = "0")
    @JsonAlias("meetingId", "meetingID")
    val meetingId: Long = 0,

    @Column("user_id")
    @JsonProperty(value = "user_id", defaultValue = "0")
    @JsonAlias("userId", "userID")
    val userId: Long = 0,

    @Column("is_host")
    @JsonProperty(value = "is_host", defaultValue = "false")
    @JsonAlias("isHost")
    val isHost: Boolean = false
) : DataModel<MeetingAttendee, MeetingAttendeeInput>
{
    override fun toInput() = MeetingAttendeeInput(
        this.meetingId,
        this.userId,
        this.isHost
    )
}
