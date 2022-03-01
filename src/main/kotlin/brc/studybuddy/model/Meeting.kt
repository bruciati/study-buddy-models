package brc.studybuddy.model

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
    @JsonProperty("group_id")
    val groupId: Long,

    @Column("host_id")
    @JsonProperty("host_id")
    val hostId: Long,

    @Column("name")
    @JsonProperty("name")
    val name: String,

    @Column("datetime")
    @JsonProperty("datetime")
    val dateTime: Long,

    @Column("type")
    @JsonProperty("type")
    val type: Type,

    @Column("location")
    @JsonProperty("location")
    val location: String
) {
    @Transient
    @JsonProperty("group")
    var group: Group? = null

    @Transient
    @JsonProperty("host")
    var host: User? = null

    @Transient
    @JsonProperty("attendees")
    var attendees: List<User>? = null

    enum class Type {
        PHYSICAL,
        ONLINE
    }
}
