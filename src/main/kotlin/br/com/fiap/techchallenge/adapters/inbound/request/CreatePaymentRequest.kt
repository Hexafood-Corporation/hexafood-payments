package br.com.fiap.techchallenge.adapters.inbound.request

import br.com.fiap.techchallenge.application.core.domain.Order
import br.com.fiap.techchallenge.application.core.domain.Payment
import br.com.fiap.techchallenge.application.core.enums.PaymentMethod
import br.com.fiap.techchallenge.application.core.enums.PaymentStatus

data class CreatePaymentRequest(
    val orderId: String? = null
) {

    fun toPayment(order: Order): Payment {
        return Payment(
            status = PaymentStatus.IN_PROCESS,
            paymentMethod = order.paymentMethod,
            order = order
        )
    }
}

data class CreatePaymentResponse(
    val paymentId: String
)