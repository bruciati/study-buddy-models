package brc.studybuddy.webclient.extension

import brc.studybuddy.graphql.model.GraphQlRequest
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec
import reactor.core.publisher.Mono

fun RequestBodyUriSpec.bodyGraphQl(
    query: String,
    variables: HashMap<String, Any>? = null
): WebClient.RequestHeadersSpec<*> =
    this.body(Mono.just(GraphQlRequest("{ $query }", variables)), GraphQlRequest::class.java)