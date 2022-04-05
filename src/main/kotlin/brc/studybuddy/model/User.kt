package brc.studybuddy.model

import com.fasterxml.jackson.annotation.JsonAlias
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

    @Column("auth_type")
    @JsonProperty(value = "auth_type", defaultValue = "PASSWORD")
    @JsonAlias("authType")
    val authType: AuthType = AuthType.PASSWORD,

    @Column("auth_value")
    @JsonProperty(value = "auth_value", defaultValue = "password")
    @JsonAlias("authValue")
    val authValue: String = "password"
) : DataModel {
    enum class AuthType {
        PASSWORD,
        FACEBOOK
    }
}
