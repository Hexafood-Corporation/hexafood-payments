package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.exception.PaymentNotFoundException
import br.com.fiap.application.ports.out.IPaymentPersistence
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class FindPaymentByIdUseCaseImplTest {

    @Test
    fun `should return correct payment when found by ID`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val findPaymentByIdUseCase = FindPaymentByIdUseCaseImpl(paymentPersistence)

        val paymentId = 123
        val payment = Payment(paymentId)

        every { paymentPersistence.findByPaymentId(paymentId.toString()) } returns payment
        val result = findPaymentByIdUseCase.findByPaymentId(paymentId.toString())

        result shouldBe payment
    }

    @Test
    fun `Should throw PaymentNotFoundException when not finding payment by ID`() {
        val paymentPersistence = mockk<IPaymentPersistence>()
        val findPaymentByIdUseCase = FindPaymentByIdUseCaseImpl(paymentPersistence)

        val nonExistentPaymentId = "456"

        every { paymentPersistence.findByPaymentId(nonExistentPaymentId) } returns null

        val exception = shouldThrow<PaymentNotFoundException> {
            findPaymentByIdUseCase.findByPaymentId(nonExistentPaymentId)
        }

        exception.message shouldBe "Payment with code: $nonExistentPaymentId not found"
    }
}
