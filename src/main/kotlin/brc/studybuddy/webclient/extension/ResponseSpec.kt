package brc.studybuddy.webclient.extension

import brc.studybuddy.graphql.model.GraphQlError
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private val objectMapper = object : ThreadLocal<ObjectMapper>() {
    override fun initialValue(): ObjectMapper = ObjectMapper()

    @Throws(IllegalArgumentException::class)
    fun <T> convertValue(value: Any?, classType: Class<T>): T = get().convertValue(value, classType)
}

fun WebClient.ResponseSpec.graphQlToMap(): Mono<Map<*, *>> =
    this.bodyToMono(Map::class.java)
        .handle { map, sink ->
            if (map.containsKey("errors")) {
                val errors = map["errors"] as List<*>
                sink.error(objectMapper.convertValue(errors.first(), GraphQlError::class.java))
            } else {
                sink.next(map["data"] as Map<*, *>)
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToMono(classType: Class<T>): Mono<T> =
    this.graphQlToMap()
        .handle { map, sink ->
            try {
                sink.next(objectMapper.convertValue(map.values.first(), classType))
            } catch (_: IllegalArgumentException) {
                sink.error(
                    GraphQlError(
                        "The result is not a Mono of type '${classType.name}'",
                        GraphQlError.Location(0, 0)
                    )
                )
            } catch (_: NoSuchElementException) {
                sink.complete()
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToFlux(classType: Class<T>): Flux<T> =
    this.graphQlToMap()
        .handle<List<*>> { map, sink ->
            when (val result = map.values.first() as? List<*>) {
                null -> sink.error(
                    GraphQlError(
                        "The result is not a Mono of type 'List<${classType.name}>'",
                        GraphQlError.Location(0, 0)
                    )
                )
                else -> sink.next(result)
            }
        }
        .flatMapIterable { it }
        .handle { value, sink ->
            try {
                sink.next(objectMapper.convertValue(value, classType))
            } catch (_: IllegalArgumentException) {
                sink.error(
                    GraphQlError(
                        "The result is not a Flux element of type '${classType.name}'",
                        GraphQlError.Location(0, 0)
                    )
                )
            } catch (_: NoSuchElementException) {
                sink.complete()
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToMonoList(classType: Class<T>): Mono<List<T>> =
    this.graphQlToFlux(classType)
        .collectList()
