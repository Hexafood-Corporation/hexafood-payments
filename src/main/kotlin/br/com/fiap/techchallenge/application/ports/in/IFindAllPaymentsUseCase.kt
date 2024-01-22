package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Payment

fun interface IFindAllPaymentsUseCase {
    fun findAll(): List<Payment>
}