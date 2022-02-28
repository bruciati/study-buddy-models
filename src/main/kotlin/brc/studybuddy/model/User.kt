package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    @JsonProperty("id")
    val id: Long,

    @Column("email")
    @JsonProperty("email")
    val email: String,

    @Column("login_type")
    @JsonProperty("login_type")
    val loginType: Type,

    @Column("login_value")
    @JsonProperty("login_value")
    val loginValue: String,

    @Column("groups")
    @JsonIgnore
    val groupIds: Long
) {
    @Transient
    @JsonProperty("groups")
    var groups: List<Group>? = null

    enum class Type {
        PASSWORD,
        FACEBOOK
    }
}
