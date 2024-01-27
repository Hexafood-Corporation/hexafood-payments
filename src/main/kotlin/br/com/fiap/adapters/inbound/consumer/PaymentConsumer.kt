package br.com.fiap.adapters.inbound.consumer

import br.com.fiap.adapters.inbound.request.CreatePaymentRequest
import br.com.fiap.adapters.inbound.response.PaymentResponse
import br.com.fiap.adapters.inbound.response.toPaymentResponse
import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.ports.`in`.ICreatePaymentUseCase
import br.com.fiap.application.ports.`in`.IFindPaymentsByStatusUseCase
import com.amazonaws.services.sqs.model.Message
import io.awspring.cloud.sqs.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.stereotype.Service


@Service
class PaymentConsumer(
    private val createPaymentUseCase: ICreatePaymentUseCase,
    ) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @SqsListener("novo_pedido")
    fun onMessage(order: CreatePaymentRequest) {
        logger.info("**** Receiving message from queue novo_pedido ****")
        createPaymentUseCase.save(order.toPayment())
    }
}