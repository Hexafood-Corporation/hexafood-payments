package br.com.fiap.techchallenge.application.ports.out

import br.com.fiap.techchallenge.application.core.domain.Payment

interface IPaymentPersistence {
    fun findAll(): List<Payment>
    fun findByPaymentId(paymentId: String): Payment?
    fun save(payment: Payment): Payment
    fun updateToApprovedStatus(paymentId: String)
    fun updateToRefusedStatus(paymentId: String)
}