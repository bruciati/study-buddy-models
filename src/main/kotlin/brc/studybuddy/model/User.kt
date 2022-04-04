package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    @JsonProperty(value = "id", defaultValue = "0")
    val id: Long = 0,

    @Column("email")
    @JsonProperty(value = "email", defaultValue = "unknown@domain.tld")
    val email: String = "unknown@domain.tld",

    @Column("login_type")
    @JsonProperty(value = "login_type", defaultValue = "PASSWORD")
    val loginType: LoginType = LoginType.PASSWORD,

    @Column("login_value")
    @JsonProperty(value = "login_value", defaultValue = "password")
    val loginValue: String = "password",
) : DataModel {
    enum class LoginType {
        PASSWORD,
        FACEBOOK
    }
}
