package br.com.fiap.application.ports.`in`

import br.com.fiap.application.core.domain.Payment

fun interface IFindAllPaymentsUseCase {
    fun findAll(): List<Payment>
}