package brc.studybuddy.rabbitmq.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.AMQP.BasicProperties
import reactor.rabbitmq.RpcClient.RpcRequest

class RpcMessage<T>(
    properties: BasicProperties?,
    body: T
) : RpcRequest(properties, serializeData(body)) {
    constructor(body: T) : this(null, body)

    companion object {
        private val objectMapper = ObjectMapper()

        private fun <T> serializeData(value: T): ByteArray =
            objectMapper.writeValueAsString(value).toByteArray(Charsets.UTF_8)

    }
}
