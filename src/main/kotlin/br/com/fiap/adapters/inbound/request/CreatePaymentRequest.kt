package br.com.fiap.adapters.inbound.request

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus

data class CreatePaymentRequest(
    val id: Long,
    val codigoPedido: String,
    val valorTotal: Double,
    val status: String,
    val cliente: Cliente
) {

    fun toPayment(): Payment {
        return Payment(
            status = PaymentStatus.IN_PROCESS,
            paymentMethod = PaymentMethod.PIX,
            order = codigoPedido,
            clientId = cliente.id
        )
    }
}


data class Cliente(
    val id: Long,
    val nome: String,
    val cpf: String
)
data class CreatePaymentResponse(
    val paymentId: String
)