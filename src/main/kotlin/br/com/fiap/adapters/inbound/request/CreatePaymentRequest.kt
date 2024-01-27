package br.com.fiap.adapters.inbound.request

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus

data class CreatePaymentRequest(
    val orderId: String? = null
) {

    fun toPayment(order: String): Payment {
        return Payment(
            status = PaymentStatus.IN_PROCESS,
            paymentMethod = PaymentMethod.CASH,
            order = order
        )
    }
}

data class CreatePaymentResponse(
    val paymentId: String
)