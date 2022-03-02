package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("members")
data class GroupMember(
    @Id
    @Column("group_id")
    @JsonProperty("group_id")
    val groupId: Long,

    @Column("user_id")
    @JsonProperty("user_id")
    val userId: Long,

    @Column("is_owner")
    @JsonProperty("is_owner")
    val isOwner: Boolean
)
