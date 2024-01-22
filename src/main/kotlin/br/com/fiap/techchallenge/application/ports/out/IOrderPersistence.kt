package br.com.fiap.techchallenge.application.ports.out

import br.com.fiap.techchallenge.application.core.domain.Order

interface IOrderPersistence {
    fun findAll(): List<Order>
    fun findByCode(orderId: String): Order?
    fun save(order: Order): Order
    fun updateToInPreparationStatus(orderId: String)
    fun updateToReadyStatus(orderId: String)
    fun updateToFinishedStatus(orderId: String)
}