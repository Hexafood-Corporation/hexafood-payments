package br.com.fiap.adapters.inbound.consumer

import br.com.fiap.adapters.inbound.request.Cliente
import br.com.fiap.adapters.inbound.request.CreatePaymentRequest
import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.ports.`in`.ICreatePaymentUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PaymentConsumerTest {

    @Test
    fun `should process message and save data in database`() {
        val createPaymentUseCase = mockk<ICreatePaymentUseCase>()
        val paymentConsumer = PaymentConsumer(createPaymentUseCase)
        val clienteMockk = mockk<Cliente>()

        every { clienteMockk.id } returns 1L

        val order = CreatePaymentRequest(
            id = 1,
            codigoPedido = "pedido123",
            valorTotal = BigDecimal.valueOf(100.00),
            status = "IN_PROCESS",
            cliente = clienteMockk
        )

        every { createPaymentUseCase.save(any()) } returns Payment(id = 1, order = "pedido123")
        paymentConsumer.onMessage(order)
        verify { createPaymentUseCase.save(any()) }
    }
}
