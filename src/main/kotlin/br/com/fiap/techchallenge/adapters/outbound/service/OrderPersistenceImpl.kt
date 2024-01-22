package br.com.fiap.techchallenge.adapters.outbound.service

import br.com.fiap.techchallenge.adapters.inbound.entity.toEntity
import br.com.fiap.techchallenge.adapters.outbound.repository.IOrderRepository
import br.com.fiap.techchallenge.application.core.domain.Order
import br.com.fiap.techchallenge.application.ports.out.IOrderPersistence
import org.springframework.stereotype.Service

@Service
class OrderPersistenceImpl(
    private val orderRepository: IOrderRepository
) : IOrderPersistence {

    override fun findAll(): List<Order> =
        orderRepository.findAll().map { it.toOrder() }

    override fun save(order: Order) =
        orderRepository.save(order.toEntity()).toOrder()

    override fun updateToInPreparationStatus(orderId: String) {
        val orderEntity = orderRepository.findByOrderId(orderId)
        orderEntity?.let {
            val order = it.toOrder()
            order.updateToInPreparationStatus()
            orderRepository.save(order.toEntity())
        }
    }

    override fun updateToReadyStatus(orderId: String) {
        val orderEntity = orderRepository.findByOrderId(orderId)
        orderEntity?.let {
            val order = it.toOrder()
            order.updateToReadyStatus()
            orderRepository.save(order.toEntity())
        }
    }

    override fun updateToFinishedStatus(orderId: String) {
        val orderEntity = orderRepository.findByOrderId(orderId)
        orderEntity?.let {
            val order = it.toOrder()
            order.updateToFinishedStatus()
            orderRepository.save(order.toEntity())
        }
    }

    override fun findByCode(orderId: String): Order? =
        orderRepository.findByOrderId(orderId)?.toOrder()

}