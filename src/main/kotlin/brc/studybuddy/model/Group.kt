package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("groups")
data class Group(
    @Id
    @JsonProperty("id")
    val id: Long = 0,

    @Column("owner_id")
    @JsonProperty("owner_id")
    val ownerId: Long = 0,

    @Column("title")
    @JsonProperty("title")
    val title: String? = null,

    @Column("description")
    @JsonProperty("description")
    val description: String? = null
) {
    @Transient
    @JsonProperty("owner")
    var owner: User? = null

    @Transient
    @JsonProperty("members")
    var members: List<User>? = null
}
