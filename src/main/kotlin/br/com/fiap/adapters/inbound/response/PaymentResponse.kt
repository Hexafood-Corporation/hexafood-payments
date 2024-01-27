package br.com.fiap.adapters.inbound.response

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class PaymentResponse(
    @JsonProperty("id_pagamento")
    var paymentId: String,

    @JsonProperty("id_pedido")
    val orderId: String,

    val status: PaymentStatus,
    val statusUpdatedAt: LocalDateTime? = null,
    val paymentMethod: PaymentMethod = PaymentMethod.PIX,
)

fun Payment.toPaymentResponse() = PaymentResponse(
    paymentId = paymentId!!,
    status = status!!,
    statusUpdatedAt = statusUpdatedAt,
    paymentMethod = paymentMethod!!,
    orderId = order!!
)