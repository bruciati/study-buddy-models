package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    @JsonProperty("id")
    val id: Long = 0,

    @Column("email")
    @JsonProperty("email")
    val email: String? = null,

    @Column("login_type")
    @JsonProperty("login_type")
    val loginType: Type = Type.PASSWORD,

    @Column("login_value")
    @JsonProperty("login_value")
    val loginValue: String? = null,

    @Column("groups")
    @JsonProperty("groups_owned")
    val groupsOwned: Long = 0
) {
    @Transient
    @JsonProperty("groups")
    var groups: List<Group>? = null

    enum class Type {
        PASSWORD,
        FACEBOOK
    }
}
