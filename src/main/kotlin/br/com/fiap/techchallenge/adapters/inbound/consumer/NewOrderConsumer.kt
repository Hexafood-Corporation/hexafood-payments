package br.com.fiap.techchallenge.adapters.inbound.consumer

import br.com.fiap.techchallenge.adapters.inbound.request.Pedido
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Service

@Service
class NewOrderConsumer {

    @SqsListener("novo_pedido")
    fun onMessage(order: Pedido) {
        println(order)
    }
}