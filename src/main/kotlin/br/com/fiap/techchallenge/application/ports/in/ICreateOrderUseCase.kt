package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Order

fun interface ICreateOrderUseCase {
    fun save(order: Order): Order
}