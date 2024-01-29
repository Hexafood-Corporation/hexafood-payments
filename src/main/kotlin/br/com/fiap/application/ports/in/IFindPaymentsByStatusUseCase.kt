package br.com.fiap.application.ports.`in`

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentStatus

fun interface IFindPaymentsByStatusUseCase {
    fun findByStatus(status: PaymentStatus): List<Payment>?
}