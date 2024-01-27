package br.com.fiap.adapters.inbound.consumer

import br.com.fiap.adapters.inbound.request.CreatePaymentRequest
import br.com.fiap.application.ports.`in`.ICreatePaymentUseCase
import io.awspring.cloud.sqs.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PaymentConsumer(
    private val createPaymentUseCase: ICreatePaymentUseCase,
    ) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @SqsListener("novo_pedido")
    fun onMessage(order: CreatePaymentRequest) {
        logger.info("**** Receiving message from queue novo_pedido with order_id=${order.id}****")

        createPaymentUseCase.save(order.toPayment())

        logger.info("**** message data was saved in database ****")
    }
}