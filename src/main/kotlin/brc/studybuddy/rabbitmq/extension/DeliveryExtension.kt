package brc.studybuddy.rabbitmq.extension

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Delivery
import java.nio.ByteBuffer

private val objectMapper = ObjectMapper()

fun <T> Delivery.getBodyClass(bodyType: Class<T>): T {
    val valueBuffer = ByteBuffer.wrap(this.body)
    val valueString = Charsets.UTF_8.decode(valueBuffer).toString()
    return objectMapper.readValue(valueString, bodyType)
}
