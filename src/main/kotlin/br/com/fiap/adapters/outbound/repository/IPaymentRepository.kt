package br.com.fiap.adapters.outbound.repository

import br.com.fiap.adapters.inbound.entity.PaymentEntity
import br.com.fiap.application.core.enums.PaymentStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IPaymentRepository : JpaRepository<PaymentEntity, Int> {
    fun findByPaymentId(paymentId: String): PaymentEntity?

    fun findByStatus(status: String): List<PaymentEntity?>

}