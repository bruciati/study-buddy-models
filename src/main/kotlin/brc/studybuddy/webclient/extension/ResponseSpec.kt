package brc.studybuddy.webclient.extension

import brc.studybuddy.graphql.model.GraphQlError
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun WebClient.ResponseSpec.graphQlToMap(): Mono<Map<String, *>> =
    this.bodyToMono(Map::class.java)
        .handle { map, sink ->
            if (map.containsKey("errors")) {
                val errors = map["errors"] as List<*>
                sink.error(errors.first() as GraphQlError)
            } else {
                sink.next(map["data"] as Map<String, *>)
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToMono(classType: Class<T>): Mono<T> = this.graphQlToMap()
    .handle { map, sink ->
        try {
            when (val result = map.values.first() as? T) {
                null -> sink.error(
                    GraphQlError(
                        "The result is not a Mono of type '${classType.name}'",
                        GraphQlError.Location(0, 0)
                    )
                )
                else -> sink.next(result)
            }
        } catch (_: NoSuchElementException) {
            sink.complete()
        }
    }

fun <T> WebClient.ResponseSpec.graphQlToMonoList(classType: Class<T>): Mono<List<T>> = this.graphQlToMap()
    .handle { map, sink ->
        when (val result = map.values.first() as? List<T>) {
            null -> sink.error(
                GraphQlError(
                    "The result is not a Mono of type 'List<${classType.name}>'",
                    GraphQlError.Location(0, 0)
                )
            )
            else -> sink.next(result)
        }
    }

fun <T> WebClient.ResponseSpec.graphQlToFlux(classType: Class<T>): Flux<T> =
    this.graphQlToMonoList(classType).flatMapIterable { it }
