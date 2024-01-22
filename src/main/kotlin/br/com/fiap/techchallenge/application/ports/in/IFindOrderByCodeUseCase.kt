package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Order

fun interface IFindOrderByCodeUseCase {
    fun findByOrderId(orderId: String): Order
}