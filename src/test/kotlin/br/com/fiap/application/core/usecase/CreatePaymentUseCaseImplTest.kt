package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.ports.out.IPaymentPersistence
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CreatePaymentUseCaseTest {

    @Test
    fun `should save the payment correctly`() {

        val paymentPersistence = mockk<IPaymentPersistence>()
        val createPaymentUseCase = CreatePaymentUseCaseImpl(paymentPersistence)

        val payment = Payment(id = 1)
        every { paymentPersistence.save(any()) } returns payment

        val result = createPaymentUseCase.save(payment)

        result shouldBe payment
        verify(exactly = 1) { paymentPersistence.save(payment) }
    }
}