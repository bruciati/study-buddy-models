package brc.studybuddy.webclient.extension

import brc.studybuddy.graphql.model.GraphQlError
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private val objectMapper = object : ThreadLocal<ObjectMapper>() {
    override fun initialValue(): ObjectMapper = jacksonObjectMapper()

    @Throws(IllegalArgumentException::class)
    fun <T> convertValue(value: Any?, classType: Class<T>): T = get().convertValue(value, classType)
}

private fun WebClient.ResponseSpec.graphQlToMap(): Mono<Map<*, *>> =
    this.bodyToMono(Map::class.java)
        .handle { map, sink ->
            if (map.containsKey("errors")) {

                val errors = map["errors"] as List<*>
                val firstError = errors.first()
                val firstErrorObj = objectMapper.convertValue(firstError, GraphQlError::class.java)
                sink.error(firstErrorObj)
            } else {
                val dataMap = map["data"] as Map<*, *>
                sink.next(dataMap)
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToMono(classType: Class<T>): Mono<T> =
    this.graphQlToMap()
        .handle { map, sink ->
            try {
                val firstValue = map.values.first()
                val firstValueObj = objectMapper.convertValue(firstValue, classType)
                sink.next(firstValueObj)
            } catch (_: IllegalArgumentException) {
                sink.error(
                    GraphQlError(
                        "The result is not a Mono of type '${classType.name}'"
                    )
                )
            } catch (_: NoSuchElementException) {
                sink.complete()
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToFlux(classType: Class<T>): Flux<T> =
    this.graphQlToMap()
        .handle<List<*>> { map, sink ->
            when (val value = map.values.first() as? List<*>) {
                null -> sink.error(
                    GraphQlError(
                        "The result is not a Mono of type 'List<${classType.name}>'"
                    )
                )
                else -> sink.next(value)
            }
        }
        .flatMapIterable { it }
        .handle { value, sink ->
            try {
                val valueObj = objectMapper.convertValue(value, classType)
                sink.next(valueObj)
            } catch (_: IllegalArgumentException) {
                sink.error(
                    GraphQlError(
                        "The result is not a Flux element of type '${classType.name}'"
                    )
                )
            }
        }
