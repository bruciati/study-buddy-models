package brc.studybuddy.graphql.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GraphQlRequest(
    @JsonProperty("query")
    val query: String,

    @JsonProperty("variables")
    val variables: HashMap<String, Any>?,

    @JsonProperty("operationName")
    val operationName: String? = null
)