package br.com.fiap.adapters.inbound.consumer

import br.com.fiap.adapters.inbound.request.Pedido
import org.springframework.stereotype.Service
import io.awspring.cloud.sqs.annotation.SqsListener

@Service
class NewOrderConsumer {

    @SqsListener("novo_pedido")
    fun onMessage(order: Pedido) {
        println(order)
    }
}