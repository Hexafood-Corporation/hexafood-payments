package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentStatus
import br.com.fiap.application.ports.out.IPaymentPersistence
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PaymentStatusManagerUseCaseImplTest {

    @Test
    fun `should update payment to approved status`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val paymentStatusManagerUseCase = PaymentStatusManagerUseCaseImpl(paymentPersistence)
        val paymentId = "123"
        val payment = Payment(id = 123, status = PaymentStatus.IN_PROCESS)
        val capturedId = slot<String>()

        every { paymentPersistence.updateToApprovedStatus(capture(capturedId)) } answers {
            payment.status = PaymentStatus.APPROVED
        }

        paymentStatusManagerUseCase.updateToApprovedStatus(paymentId)

        assertTrue(capturedId.isCaptured)
        assertEquals(paymentId, capturedId.captured)
        assertEquals(PaymentStatus.APPROVED, payment.status)
    }

    @Test
    fun `should update payment to refused status`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val paymentStatusManagerUseCase = PaymentStatusManagerUseCaseImpl(paymentPersistence)
        val paymentId = "456"
        val payment = Payment(id = 456, status = PaymentStatus.IN_PROCESS)
        val capturedId = slot<String>()

        every { paymentPersistence.updateToRefusedStatus(capture(capturedId)) } answers {
            payment.status = PaymentStatus.REFUSED
        }

        paymentStatusManagerUseCase.updateToRefusedStatus(paymentId)

        assertTrue(capturedId.isCaptured)
        assertEquals(paymentId, capturedId.captured)
        assertEquals(PaymentStatus.REFUSED, payment.status)
    }

}
