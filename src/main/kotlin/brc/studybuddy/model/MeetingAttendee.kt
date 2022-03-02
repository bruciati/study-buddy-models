package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("attendees")
data class MeetingAttendee(
    @Id
    @Column("meeting_id")
    @JsonProperty("meeting_id")
    val meetingId: Long,

    @Column("user_id")
    @JsonProperty("user_id")
    val userId: Long,

    @Column("is_host")
    @JsonProperty("is_host")
    val isHost: Boolean
)
