package brc.studybuddy.database.model

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
    val loginType: LoginType,

    @Column("login_value")
    @JsonProperty("login_value")
    val loginValue: String,

    @Column("groups")
    @JsonProperty("groups")
    val groups: Long
) {
    enum class LoginType {
        PASSWORD,
        FACEBOOK
    }
}
