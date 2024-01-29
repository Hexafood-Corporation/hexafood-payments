package br.com.fiap.adapters.inbound.entity

import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat

class PaymentEntityTest {

    @Test
    fun `should convert PaymentEntity to Payment`() {
        val paymentId = UUID.randomUUID().toString()
        val orderId = "12345"
        val clientId = 1L
        val status = "APPROVED"
        val statusUpdatedAt = LocalDateTime.now()
        val paymentMethod = "PIX"

        val paymentEntity = PaymentEntity(
            id = 1,
            paymentId = paymentId,
            order = orderId,
            clientId = clientId,
            status = status,
            statusUpdatedAt = statusUpdatedAt,
            paymentMethod = paymentMethod
        )

        val payment = paymentEntity.toPayment()

        assertThat(payment.id).isEqualTo(1)
        assertThat(payment.paymentId).isEqualTo(paymentId)
        assertThat(payment.order).isEqualTo(orderId)
        assertThat(payment.clientId).isEqualTo(clientId)
        assertThat(payment.status).isEqualTo(PaymentStatus.APPROVED)
        assertThat(payment.statusUpdatedAt).isEqualTo(statusUpdatedAt)
        assertThat(payment.paymentMethod).isEqualTo(PaymentMethod.PIX)
    }

    @Test
    fun `should generate paymentId on PrePersist`() {
        val paymentEntity = PaymentEntity(
            order = "123",
            clientId = 1,
            paymentMethod = "PIX"
        )

        assertThat(paymentEntity.paymentId).isNull()

        paymentEntity.generate()

        assertThat(paymentEntity.paymentId).isNotNull()
    }

    @Test
    fun `should have valid equals and hashCode methods`() {
        val paymentEntity1 = PaymentEntity(
            id = 1,
            order = "1",
            clientId = 1L,
            paymentMethod = PaymentMethod.CASH.toString()
        )

        val paymentEntity2 = PaymentEntity(
            id = 1,
            order = "1",
            clientId = 1L,
            paymentMethod = PaymentMethod.CASH.toString()
        )

        val paymentEntity3 = PaymentEntity(
            id = 2,
            order = "2",
            clientId = 2L,
            paymentMethod = PaymentMethod.CREDIT_CARD.toString()
        )

        assertThat(paymentEntity1).isEqualTo(paymentEntity1)
        assertThat(paymentEntity1.hashCode()).isEqualTo(paymentEntity1.hashCode())

        assertThat(paymentEntity1).isEqualTo(paymentEntity2)
        assertThat(paymentEntity1.hashCode()).isEqualTo(paymentEntity2.hashCode())

        assertThat(paymentEntity1).isNotEqualTo(paymentEntity3)
        assertThat(paymentEntity1.hashCode()).isNotEqualTo(paymentEntity3.hashCode())
    }
}
