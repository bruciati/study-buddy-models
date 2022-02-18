package brc.studybuddy.rabbitmq

import com.fasterxml.jackson.databind.ObjectMapper
import java.nio.ByteBuffer

internal val objectMapper = ObjectMapper()

internal fun <T> serializeData(value: T): ByteArray = objectMapper.writeValueAsString(value).toByteArray(Charsets.UTF_8)

internal fun <T> deserializeData(value: ByteArray, valueType: Class<T>): T {
    val valueBuffer = ByteBuffer.wrap(value)
    val valueString = Charsets.UTF_8.decode(valueBuffer).toString()
    return objectMapper.readValue(valueString, valueType)
}
