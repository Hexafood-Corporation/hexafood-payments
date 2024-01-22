package br.com.fiap.techchallenge.adapters.outbound.repository

import br.com.fiap.techchallenge.adapters.inbound.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOrderRepository : JpaRepository<OrderEntity, Int>{
    fun findByOrderId(orderId: String): OrderEntity?
}