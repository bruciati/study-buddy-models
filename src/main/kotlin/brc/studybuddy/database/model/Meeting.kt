package brc.studybuddy.database.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("meetings")
data class Meeting(
    @Id
    @JsonProperty("id")
    val id: Long,

    @Column("group_id")
    @JsonIgnore
    val groupId: Long,

    @Column("host_id")
    @JsonIgnore
    val hostId: Long,

    @Column("name")
    @JsonProperty("name")
    val name: String,

    @Column("datetime")
    @JsonProperty("datetime")
    val dateTime: Long,

    @Column("type")
    @JsonProperty("type")
    val type: MeetingType,

    @Column("location")
    @JsonProperty("location")
    val location: String
) {
    @Transient
    @JsonProperty("group")
    lateinit var group: Group

    @Transient
    @JsonProperty("host")
    lateinit var host: User

    enum class MeetingType {
        PHYSICAL,
        ONLINE
    }
}
