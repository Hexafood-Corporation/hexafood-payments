package br.com.fiap.application.core.domain

import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus
import java.time.LocalDateTime

data class Payment(
    val id: Int? = null,
    val paymentId: String? = null,
    val order: String? = null,
    var status: PaymentStatus? = PaymentStatus.IN_PROCESS,
    var clientId: Long? = null,
    var statusUpdatedAt: LocalDateTime? = null,
    val paymentMethod: PaymentMethod? = null,
) {

    fun updateToApprovedStatus() {
        status = PaymentStatus.APPROVED
        statusUpdatedAt = LocalDateTime.now()
    }

    fun updateToRefusedStatus() {
        status = PaymentStatus.REFUSED
        statusUpdatedAt = LocalDateTime.now()
    }

    override fun toString(): String {
        return "Payment(id=$id, paymentId=$paymentId, orderId=$order, status=$status, statusUpdatedAt=$statusUpdatedAt, paymentMethod=$paymentMethod)"
    }
}