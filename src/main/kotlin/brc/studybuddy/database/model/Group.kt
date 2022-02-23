package brc.studybuddy.database.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("groups")
data class Group(
    @Id
    @JsonProperty("id")
    val id: Long,

    @Column("owner_id")
    @JsonIgnore
    val ownerId: Long,

    @Column("title")
    @JsonProperty("title")
    val title: String,

    @Column("description")
    @JsonProperty("description")
    val description: String?
) {
    @Transient
    @JsonProperty("owner")
    var owner: User? = null

    @Transient
    @JsonProperty("members")
    val members: List<User> = emptyList()
}
