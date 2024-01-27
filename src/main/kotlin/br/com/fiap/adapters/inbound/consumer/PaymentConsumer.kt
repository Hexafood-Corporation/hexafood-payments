package br.com.fiap.adapters.inbound.consumer

import br.com.fiap.adapters.inbound.request.CreatePaymentRequest
import br.com.fiap.application.ports.`in`.ICreatePaymentUseCase
import com.amazonaws.services.sqs.model.Message
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.stereotype.Service


@Service
class PaymentConsumer(
    private val createPaymentUseCase: ICreatePaymentUseCase,
    private val queueMessagingTemplate: QueueMessagingTemplate
) {

    @SqsListener("novo_pedido")
    fun onMessage(order: CreatePaymentRequest) {
        createPaymentUseCase.save(order.toPayment())
        sendMessageToSqs()
    }

    fun sendMessageToSqs() {
        queueMessagingTemplate.convertAndSend<Any>("pagamento_processado", "message") //send to queue
    }
}