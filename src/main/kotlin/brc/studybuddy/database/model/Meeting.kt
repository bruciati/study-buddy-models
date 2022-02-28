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

    enum class Type {
        PHYSICAL,
        ONLINE
    }
}
