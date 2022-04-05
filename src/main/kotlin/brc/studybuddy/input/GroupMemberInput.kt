package brc.studybuddy.input

import brc.studybuddy.model.GroupMember
import com.fasterxml.jackson.annotation.*

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GroupMemberInput(
    @JsonProperty(value = "group_id", defaultValue = "0")
    @JsonAlias("groupId", "groupID")
    var groupId: Long? = null,

    @JsonProperty(value = "user_id", defaultValue = "0")
    @JsonAlias("userId", "userID")
    var userId: Long? = null,

    @JsonProperty(value = "is_owner", defaultValue = "false")
    @JsonAlias("isOwner")
    var isOwner: Boolean? = null
) : DataInput<GroupMember, GroupMemberInput>
{
    override fun toModel() = GroupMember(
        this.groupId!!,
        this.userId!!,
        this.isOwner ?: false
    )

    override fun updateModel(model: GroupMember) = GroupMember(
        model.groupId,
        model.userId,
        this.isOwner ?: model.isOwner
    )
}
