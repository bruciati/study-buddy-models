package brc.studybuddy.graphql.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GraphQlError (
    @JsonProperty("message")
    override val message: String,

    @JsonProperty("location")
    val location: Location
) : Exception() {
    data class Location(
        @JsonProperty("line")
        val line: Int,

        @JsonProperty("column")
        val column: Int
    )
}
