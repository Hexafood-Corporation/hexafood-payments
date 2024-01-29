package br.com.fiap.application.ports.`in`

import br.com.fiap.application.core.domain.Payment

fun interface IFindPaymentByIdUseCase {
    fun findByPaymentId(paymentId: String): Payment
}