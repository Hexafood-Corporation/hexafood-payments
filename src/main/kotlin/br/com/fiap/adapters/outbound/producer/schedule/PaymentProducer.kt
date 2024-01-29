package br.com.fiap.adapters.outbound.producer.schedule

import br.com.fiap.adapters.inbound.response.PaymentResponse
import br.com.fiap.adapters.inbound.response.toPaymentResponse
import br.com.fiap.application.core.enums.PaymentStatus
import br.com.fiap.application.ports.`in`.IFindPaymentsByStatusUseCase
import br.com.fiap.application.ports.`in`.IPaymentStatusManagerUseCase
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class PaymentProducer(
    private val queueMessagingTemplate: QueueMessagingTemplate,
    private val repository: IFindPaymentsByStatusUseCase,
    private val paymentStatus: IPaymentStatusManagerUseCase
)
{
    private val logger = LoggerFactory.getLogger(javaClass)
    private val queueFinishedPayment = "pagamento_processado"

    @Scheduled(fixedDelay = 1000)
    fun SendMessage() {
        val paymentesInProcess = repository.findByStatus(PaymentStatus.IN_PROCESS)

        paymentesInProcess?.forEach {
            logger.info("**** Processing Payment ****")
            paymentStatus.updateToApprovedStatus(it.paymentId!!)
            it.status = PaymentStatus.APPROVED

            logger.info("**** Publishing payment APPROVED with order_id= ${it.order} to queue ****")

            sendMessageToSqs(queueFinishedPayment, it.toPaymentResponse())
        }

    }
    fun sendMessageToSqs(queueName: String, message: PaymentResponse) {

        logger.info("**** Published message to pagamento_processado queue ****")

        queueMessagingTemplate.convertAndSend<Any>(queueName, message) //send to queue
    }
}