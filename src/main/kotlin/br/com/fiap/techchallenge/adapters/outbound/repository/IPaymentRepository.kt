package br.com.fiap.techchallenge.adapters.outbound.repository

import br.com.fiap.techchallenge.adapters.inbound.entity.ClientEntity
import br.com.fiap.techchallenge.adapters.inbound.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IPaymentRepository : JpaRepository<PaymentEntity, Int> {
    fun findByPaymentId(paymentId: String): PaymentEntity?

}