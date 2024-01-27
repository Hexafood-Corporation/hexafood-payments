package br.com.fiap.application.ports.out

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentStatus

interface IPaymentPersistence {
    fun findAll(): List<Payment>
    fun findByPaymentId(paymentId: String): Payment?
    fun save(payment: Payment): Payment
    fun updateToApprovedStatus(paymentId: String)
    fun updateToRefusedStatus(paymentId: String)

    fun findByStatus(status: PaymentStatus) : List<Payment>
}