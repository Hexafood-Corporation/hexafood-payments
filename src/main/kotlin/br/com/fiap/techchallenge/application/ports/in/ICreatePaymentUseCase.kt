package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Payment

fun interface ICreatePaymentUseCase {
    fun save(paymend: Payment): Payment
}