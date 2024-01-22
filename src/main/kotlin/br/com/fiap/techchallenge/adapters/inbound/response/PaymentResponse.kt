package br.com.fiap.techchallenge.adapters.inbound.response

import br.com.fiap.techchallenge.application.core.domain.Payment
import br.com.fiap.techchallenge.application.core.enums.PaymentMethod
import br.com.fiap.techchallenge.application.core.enums.PaymentStatus
import java.time.LocalDateTime

data class PaymentResponse(
    var paymentId: String,
    val orderId: String,
    val status: PaymentStatus = PaymentStatus.IN_PROCESS,
    val statusUpdatedAt: LocalDateTime? = null,
    val paymentMethod: PaymentMethod,
)

fun Payment.toPaymentResponse() = PaymentResponse(
    paymentId = paymentId!!,
    status = status!!,
    statusUpdatedAt = statusUpdatedAt,
    paymentMethod = paymentMethod!!,
    orderId = order?.orderId!!
)