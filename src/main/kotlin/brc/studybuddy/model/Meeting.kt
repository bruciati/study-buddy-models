package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("meetings")
data class Meeting(
    @Id
    @JsonProperty(value = "id", defaultValue = "-1")
    val id: Long = -1,

    @Column("group_id")
    @JsonProperty(value = "group_id", defaultValue = "-1")
    val groupId: Long = -1,

    @Column("name")
    @JsonProperty(value = "name", defaultValue = "unknown")
    val name: String = "unknown",

    @Column("datetime")
    @JsonProperty(value = "datetime", defaultValue = "0")
    val dateTime: Long = 0,

    @Column("type")
    @JsonProperty(value = "type", defaultValue = "PHYSICAL")
    val type: Type = Type.PHYSICAL,

    @Column("location")
    @JsonProperty(value = "location", defaultValue = "unknown")
    val location: String = "unknown"
) {
    enum class Type {
        PHYSICAL,
        ONLINE
    }
}
