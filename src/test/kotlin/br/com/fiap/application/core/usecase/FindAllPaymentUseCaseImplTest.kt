package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.ports.out.IPaymentPersistence
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class FindAllPaymentsUseCaseImplTest {

    @Test
    fun `should return payment list correctly`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val findAllPaymentsUseCase = FindAllPaymentsUseCaseImpl(paymentPersistence)

        val payments = listOf(
            Payment(id = 1),
            Payment(id = 2),
            Payment(id = 3)
        )

        every { paymentPersistence.findAll() } returns payments
        val result = findAllPaymentsUseCase.findAll()

        result shouldBe payments
    }

    @Test
    fun `Should return an empty list if no payments are found`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val findAllPaymentsUseCase = FindAllPaymentsUseCaseImpl(paymentPersistence)

        every { paymentPersistence.findAll() } returns emptyList()
        val result = findAllPaymentsUseCase.findAll()

        result shouldBe emptyList()
    }
}
