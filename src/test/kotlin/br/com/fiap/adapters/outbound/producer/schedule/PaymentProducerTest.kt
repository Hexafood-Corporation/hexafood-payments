package br.com.fiap.adapters.outbound.producer.schedule

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus
import br.com.fiap.application.ports.`in`.IFindPaymentsByStatusUseCase
import br.com.fiap.application.ports.`in`.IPaymentStatusManagerUseCase
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import java.time.LocalDateTime

class PaymentProducerTest {

    private val queueMessagingTemplate = mockk<QueueMessagingTemplate>()
    private val findPaymentsByStatusUseCase = mockk<IFindPaymentsByStatusUseCase>()
    private val paymentStatusManagerUseCase = mockk<IPaymentStatusManagerUseCase>()

    private val paymentProducer = PaymentProducer(
        queueMessagingTemplate,
        findPaymentsByStatusUseCase,
        paymentStatusManagerUseCase
    )

    @Test
    fun `should send message to SQS when payment is in process`() {
        val paymentStatus = PaymentStatus.IN_PROCESS
        val paymentInProcess1 = createPayment(1, "1", "order1", 1, PaymentStatus.IN_PROCESS)
        val paymentInProcess2 = createPayment(2, "2", "order2", 2, PaymentStatus.IN_PROCESS)

        every { findPaymentsByStatusUseCase.findByStatus(paymentStatus) } returns listOf(
            paymentInProcess1,
            paymentInProcess2
        )
        every { paymentStatusManagerUseCase.updateToApprovedStatus(any()) } just runs
        every { queueMessagingTemplate.convertAndSend<Any>(any(), any()) } just runs

        paymentProducer.SendMessage()

        verify { findPaymentsByStatusUseCase.findByStatus(paymentStatus) }
        verify { paymentStatusManagerUseCase.updateToApprovedStatus(any()) }
        verify { queueMessagingTemplate.convertAndSend<Any>(any(), any()) }
    }


    @Test
    fun `should not send message to SQS when no payments are in process`() {
        val paymentStatus = PaymentStatus.IN_PROCESS
        val paymentsInProcess = emptyList<Payment>()

        every { findPaymentsByStatusUseCase.findByStatus(paymentStatus) } returns paymentsInProcess

        paymentProducer.SendMessage()

        verify { findPaymentsByStatusUseCase.findByStatus(paymentStatus) }
        verify(exactly = 0) { paymentStatusManagerUseCase.updateToApprovedStatus(any()) }
        verify(exactly = 0) { queueMessagingTemplate.convertAndSend<Any>(any(), any()) }
    }

    private fun createPayment(
        id: Int,
        paymentId: String,
        order: String,
        clientId: Long,
        status: PaymentStatus,
        statusUpdatedAt: LocalDateTime? = null,
        paymentMethod: PaymentMethod = PaymentMethod.PIX
    ): Payment {
        return Payment(
            id = id,
            paymentId = paymentId,
            order = order,
            clientId = clientId,
            status = status,
            statusUpdatedAt = statusUpdatedAt,
            paymentMethod = paymentMethod
        )
    }
}
