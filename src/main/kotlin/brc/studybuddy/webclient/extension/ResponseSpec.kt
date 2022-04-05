package brc.studybuddy.webclient.extension

import brc.studybuddy.graphql.model.GraphQlResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private val objectMapper = object : ThreadLocal<ObjectMapper>() {
    override fun initialValue(): ObjectMapper = jacksonObjectMapper()

    fun <T> convertValue(value: Any?, classType: Class<T>): T = get().convertValue(value, classType)
}

private fun WebClient.ResponseSpec.graphQlToDataEntry(): Mono<Any> =
    this.bodyToMono(GraphQlResponse::class.java)
        .handle { r, s ->
            if (r.errors != null && r.errors.isNotEmpty()) {
                val error = r.errors.first()
                s.error(error)
            } else if (r.data != null) {
                try {
                    when (val value = r.data.values.first()) {
                        null -> s.complete()
                        else -> s.next(value)
                    }
                } catch (e: NoSuchElementException) {
                    s.error(e)
                }
            } else {
                s.complete()
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToMono(classType: Class<T>): Mono<T> =
    this.graphQlToDataEntry()
        .handle { v, s ->
            try {
                val objectValue = objectMapper.convertValue(v, classType)
                s.next(objectValue)
            } catch (_: IllegalArgumentException) {
                s.error(
                    Exception("The result is not a Mono of type '${classType.name}'")
                )
            }
        }

fun <T> WebClient.ResponseSpec.graphQlToFlux(classType: Class<T>): Flux<T> =
    this.graphQlToDataEntry()
        .handle<List<*>> { v, s ->
            try {
                val listValue = v as List<*>
                s.next(listValue)
            } catch (_: ClassCastException) {
                s.error(
                    Exception("The result is not a Flux element of type '${classType.name}'")
                )
            }
        }
        .flatMapIterable { l -> l }
        .handle { v, s ->
            try {
                val objectValue = objectMapper.convertValue(v, classType)
                s.next(objectValue)
            } catch (_: IllegalArgumentException) {
                s.error(
                    Exception("The result is not a Flux element of type '${classType.name}'")
                )
            }
        }
