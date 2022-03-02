package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("groups")
data class Group(
    @Id
    @JsonProperty(value = "id", defaultValue = "-1")
    val id: Long = -1,

    @Column("title")
    @JsonProperty(value = "title", defaultValue = "unknown")
    val title: String = "unknown",

    @Column("description")
    @JsonProperty(value = "description", defaultValue = "unknown")
    val description: String = "unknown"
)
