package br.com.fiap.adapters.outbound.service

import br.com.fiap.adapters.inbound.entity.PaymentEntity
import br.com.fiap.adapters.outbound.repository.IPaymentRepository
import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentPersistenceImplTest {

    private val paymentRepository = mockk<IPaymentRepository>()
    private val paymentPersistence = PaymentPersistenceImpl(paymentRepository)

    @Test
    fun `should find payment by paymentId`() {
        val paymentId = "123"
        val paymentEntity = PaymentEntity(
            id = 1,
            paymentId = paymentId,
            order = "1",
            clientId = 1,
            status = PaymentStatus.IN_PROCESS.toString(),
            statusUpdatedAt = LocalDateTime.now(),
            paymentMethod = PaymentMethod.PIX.name,
            totalValue = BigDecimal.valueOf(100.00)
        )
        every { paymentRepository.findByPaymentId(paymentId) } returns paymentEntity

        val result = paymentPersistence.findByPaymentId(paymentId)

        assertNotNull(result)
        assertEquals(paymentEntity.toPayment(), result)
        verify { paymentRepository.findByPaymentId(paymentId) }
    }

    @Test
    fun `should find all payments`() {
        val payments = listOf(
            PaymentEntity(
                id = 1,
                paymentId = "1",
                order = "1",
                clientId = 1,
                status = PaymentStatus.IN_PROCESS.toString(),
                statusUpdatedAt = LocalDateTime.now(),
                paymentMethod = PaymentMethod.PIX.toString(),
                totalValue = BigDecimal.valueOf(100.00)

            ),
            PaymentEntity(
                id = 2,
                paymentId = "2",
                order = "2",
                clientId = 2,
                status = PaymentStatus.APPROVED.toString(),
                statusUpdatedAt = LocalDateTime.now(),
                paymentMethod = PaymentMethod.CASH.toString(),
                totalValue = BigDecimal.valueOf(100.00)

            )
        )
        every { paymentRepository.findAll() } returns payments

        val result = paymentPersistence.findAll()

        assertEquals(payments.map { it.toPayment() }, result)
        verify { paymentRepository.findAll() }
    }

    @Test
    fun `should save payment`() {
        val payment = PaymentEntity(
            id = 1,
            paymentId = "1",
            order = "1",
            clientId = 1,
            status = PaymentStatus.IN_PROCESS.toString(),
            statusUpdatedAt = LocalDateTime.now(),
            paymentMethod = PaymentMethod.PIX.toString(),
            totalValue = BigDecimal.valueOf(100.00)

        )
        every { paymentRepository.save(any()) } returns payment

        val result = paymentPersistence.save(payment.toPayment())

        assertNotNull(result)
        verify { paymentRepository.save(any()) }
    }

    @Test
    fun `should update payment to approved status`() {
        val paymentId = "123"
        val paymentEntity = PaymentEntity(
            id = 1,
            paymentId = paymentId,
            order = "1",
            clientId = 1,
            status = PaymentStatus.IN_PROCESS.toString(),
            statusUpdatedAt = LocalDateTime.now(),
            paymentMethod = PaymentMethod.PIX.toString(),
            totalValue = BigDecimal.valueOf(100.00)

        )
        every { paymentRepository.findByPaymentId(paymentId) } returns paymentEntity
        every { paymentRepository.save(any()) } returns paymentEntity

        paymentPersistence.updateToApprovedStatus(paymentId)

        verify { paymentRepository.save(any()) }
    }

    @Test
    fun `should update payment to refused status`() {
        val paymentId = "123"
        val paymentEntity = PaymentEntity(
            id = 1,
            paymentId = paymentId,
            order = "1",
            clientId = 1,
            status = PaymentStatus.IN_PROCESS.toString(),
            statusUpdatedAt = LocalDateTime.now(),
            paymentMethod = PaymentMethod.PIX.toString(),
            totalValue = BigDecimal.valueOf(100.00)

        )
        every { paymentRepository.findByPaymentId(paymentId) } returns paymentEntity
        every { paymentRepository.save(any()) } returns paymentEntity

        paymentPersistence.updateToRefusedStatus(paymentId)

        verify { paymentRepository.save(any()) }
    }

    @Test
    fun `should find payments by status`() {
        val status = PaymentStatus.APPROVED
        val payments = listOf(
            PaymentEntity(
                id = 1,
                paymentId = "1",
                order = "1",
                clientId = 1,
                status = PaymentStatus.APPROVED.toString(),
                statusUpdatedAt = LocalDateTime.now(),
                paymentMethod = PaymentMethod.PIX.toString(),
                totalValue = BigDecimal.valueOf(100.00)

            ),
            PaymentEntity(
                id = 2,
                paymentId = "2",
                order = "2",
                clientId = 2,
                status = PaymentStatus.APPROVED.toString(),
                statusUpdatedAt = LocalDateTime.now(),
                paymentMethod = PaymentMethod.CASH.toString(),
                totalValue = BigDecimal.valueOf(100.00)

            )
        )
        every { paymentRepository.findByStatus(status.name) } returns payments

        val result = paymentPersistence.findByStatus(status)

        assertEquals(payments.map { it.toPayment() }, result)
        verify { paymentRepository.findByStatus(status.name) }
    }
}
