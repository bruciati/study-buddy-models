package brc.studybuddy.rabbitmq.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client.Delivery
import reactor.rabbitmq.OutboundMessage

class TxMessage<T>(
    routingKey: String,
    properties: BasicProperties?,
    body: T
) : OutboundMessage("", routingKey, properties, serializeData(body)) {
    constructor(routingKey: String, body: T) : this(
        routingKey,
        null,
        body
    )

    // Direct Reply-To
    constructor(routingKey: String, correlationId: String, body: T) : this(
        routingKey,
        BasicProperties.Builder().correlationId(correlationId).build(),
        body
    )

    // Direct Reply-To
    constructor(delivery: Delivery, body: T) : this(
        delivery.properties.replyTo,
        BasicProperties.Builder().correlationId(delivery.properties.correlationId).build(),
        body
    )

    companion object {
        private val objectMapper = ObjectMapper()

        private fun <T> serializeData(value: T): ByteArray =
            objectMapper.writeValueAsString(value).toByteArray(Charsets.UTF_8)

    }
}
