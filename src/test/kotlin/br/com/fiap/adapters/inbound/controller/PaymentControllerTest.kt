package br.com.fiap.adapters.inbound.controller

import br.com.fiap.adapters.inbound.request.Cliente
import br.com.fiap.adapters.inbound.request.CreatePaymentRequest
import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus
import br.com.fiap.application.ports.`in`.ICreatePaymentUseCase
import br.com.fiap.application.ports.`in`.IFindAllPaymentsUseCase
import br.com.fiap.application.ports.`in`.IPaymentStatusManagerUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentControllerTest {

    private val createPaymentUseCase = mockk<ICreatePaymentUseCase>()
    private val findAllPayments = mockk<IFindAllPaymentsUseCase>()
    private val paymentStatusManager = mockk<IPaymentStatusManagerUseCase>()

    private val paymentController =
        PaymentController(paymentStatusManager, createPaymentUseCase, findAllPayments)

    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(paymentController).build()

    @Test
    fun `should return all payments`() {
        val payments = listOf(
            Payment(
                id = 1,
                paymentId = "1",
                order = "1",
                statusUpdatedAt = LocalDateTime.now(),
                paymentMethod = PaymentMethod.PIX
            ),
            Payment(
                id = 2,
                paymentId = "2",
                order = "2",
                statusUpdatedAt = LocalDateTime.now(),
                paymentMethod = PaymentMethod.CASH
            ),
        )
        every { findAllPayments.findAll() } returns payments

        mockMvc.perform(get("/api/v1/payments"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    fun `should create a new payment`() {
        val createPaymentRequest = CreatePaymentRequest(
            id = 1L,
            codigoPedido = "12345",
            valorTotal = BigDecimal.valueOf(100.00),
            status = PaymentStatus.IN_PROCESS.toString(),
            cliente = Cliente(
                id = 1L,
                nome = "Cliente 1",
                cpf = "111.222.333-40"
            )
        )
        val createdPayment = Payment(
            id = 1,
            paymentId = "1",
            order = "1",
            clientId = 1L,
            statusUpdatedAt = LocalDateTime.now(),
            paymentMethod = PaymentMethod.CASH
        )

        every { createPaymentUseCase.save(any()) } returns createdPayment

        mockMvc.perform(
            post("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createPaymentRequest.toJson())
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.paymentId").value(1))
    }

    @Test
    fun `should update payment to approved status`() {
        val paymentId = "123"
        every { paymentStatusManager.updateToApprovedStatus(paymentId) } returns Unit

        mockMvc.perform(put("/api/v1/payments/{paymentId}/approved", paymentId))
            .andExpect(status().isNoContent)

    }

    @Test
    fun `should update payment to refused status`() {
        val paymentId = "456"
        every { paymentStatusManager.updateToRefusedStatus(paymentId) } returns Unit

        mockMvc.perform(put("/api/v1/payments/{paymentId}/refused", paymentId))
            .andExpect(status().isNoContent)
    }

    private fun Any.toJson(): String = ObjectMapper().writeValueAsString(this)
}
