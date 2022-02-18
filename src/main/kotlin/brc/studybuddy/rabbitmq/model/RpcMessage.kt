package brc.studybuddy.rabbitmq.model

import brc.studybuddy.rabbitmq.serializeData
import com.rabbitmq.client.AMQP.BasicProperties
import reactor.rabbitmq.RpcClient.RpcRequest

class RpcMessage<T>(
    properties: BasicProperties?, body: T
) : RpcRequest(properties, serializeData(body)) {
    constructor(body: T) : this(null, body)
}
