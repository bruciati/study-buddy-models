package brc.studybuddy.rabbitmq.extension

import brc.studybuddy.rabbitmq.deserializeData
import com.rabbitmq.client.Delivery

fun <T> Delivery.getBodyClass(bodyType: Class<T>): T = deserializeData(this.body, bodyType)
