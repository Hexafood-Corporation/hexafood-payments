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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Payment

        if (id != other.id) return false
        if (paymentId != other.paymentId) return false
        if (order != other.order) return false
        if (status != other.status) return false
        if (statusUpdatedAt != other.statusUpdatedAt) return false
        return paymentMethod == other.paymentMethod
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (paymentId?.hashCode() ?: 0)
        result = 31 * result + order.hashCode()
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (statusUpdatedAt?.hashCode() ?: 0)
        result = 31 * result + (paymentMethod?.hashCode() ?: 0)
        return result
    }
}