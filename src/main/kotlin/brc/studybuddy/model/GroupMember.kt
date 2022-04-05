package brc.studybuddy.model

import brc.studybuddy.input.GroupMemberInput
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("members")
data class GroupMember(
    @Column("group_id")
    @JsonProperty(value = "group_id", defaultValue = "0")
    @JsonAlias("groupId", "groupID")
    val groupId: Long = 0,

    @Column("user_id")
    @JsonProperty(value = "user_id", defaultValue = "0")
    @JsonAlias("userId", "userID")
    val userId: Long = 0,

    @Column("is_owner")
    @JsonProperty(value = "is_owner", defaultValue = "false")
    @JsonAlias("isOwner")
    val isOwner: Boolean = false
) : DataModel<GroupMember, GroupMemberInput>
{
    override fun toInput() = GroupMemberInput(
        this.groupId,
        this.userId,
        this.isOwner
    )
}
