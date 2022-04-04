package brc.studybuddy.graphql.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GraphQlError(
    @JsonProperty("message")
    override val message: String
) : Exception(message)

