package brc.studybuddy.graphql.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
internal data class GraphQlRequest(
    @JsonProperty("query")
    val query: String,

    @JsonProperty("variables")
    val variables: Map<String, Any>?
)
