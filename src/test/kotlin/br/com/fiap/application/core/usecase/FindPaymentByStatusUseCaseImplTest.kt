package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentStatus
import br.com.fiap.application.ports.out.IPaymentPersistence
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FindPaymentsByStatusUseCaseImplTest {

    @Test
    fun `should return the correct list of payments by status`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val findPaymentsByStatusUseCase = FindPaymentsByStatusUseCaseImpl(paymentPersistence)

        val status = PaymentStatus.IN_PROCESS
        val payments = listOf(
            Payment(id = 1, status = PaymentStatus.IN_PROCESS),
            Payment(id = 2, status = PaymentStatus.APPROVED),
        )

        every { paymentPersistence.findByStatus(status) } returns payments
        val result = findPaymentsByStatusUseCase.findByStatus(status)

        assertEquals(payments, result)
    }

    @Test
    fun `should return an empty list if no payments are found by status`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val findPaymentsByStatusUseCase = FindPaymentsByStatusUseCaseImpl(paymentPersistence)

        val nonExistentStatus = PaymentStatus.IN_PROCESS

        every { paymentPersistence.findByStatus(nonExistentStatus)} returns (emptyList())

        val result = findPaymentsByStatusUseCase.findByStatus(nonExistentStatus)
        assertTrue(result.isEmpty())
    }

}
