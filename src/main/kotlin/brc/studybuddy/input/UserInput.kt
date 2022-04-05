package brc.studybuddy.input

import brc.studybuddy.model.User
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserInput(
    @JsonProperty(value = "id")
    var id: Long? = null,

    @JsonProperty(value = "email")
    var email: String? = null,

    @JsonProperty(value = "auth_type")
    @JsonAlias("authType")
    var authType: User.AuthType? = null,

    @JsonProperty(value = "auth_value")
    @JsonAlias("authValue")
    var authValue: String? = null
) : DataInput<User, UserInput>
{
    override fun toModel() = User(
        email = this.email!!,
        authType = this.authType!!,
        authValue = this.authValue!!
    )

    override fun updateModel(model: User) = User(
        model.id,
        this.email ?: model.email,
        this.authType ?: model.authType,
        this.authValue ?: model.authValue
    )
}
