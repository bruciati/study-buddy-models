package brc.studybuddy.graphql.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GraphQlResponse(
    @JsonProperty("errors")
    val errors: List<GraphQlError>?,

    @JsonProperty("data")
    val data: Map<String, Any?>?
)
