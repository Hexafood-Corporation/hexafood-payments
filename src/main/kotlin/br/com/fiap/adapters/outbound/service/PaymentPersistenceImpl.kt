package br.com.fiap.adapters.outbound.service

import br.com.fiap.adapters.inbound.entity.toEntity
import br.com.fiap.adapters.outbound.repository.IPaymentRepository
import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentStatus
import br.com.fiap.application.ports.out.IPaymentPersistence
import org.springframework.stereotype.Service

@Service
class PaymentPersistenceImpl(
    private val paymentRepository: IPaymentRepository
) : IPaymentPersistence {

    override fun findByPaymentId(paymentId: String): Payment? =
        paymentRepository.findByPaymentId(paymentId)?.toPayment()

    override fun findAll() =
        paymentRepository.findAll().map { it.toPayment() }


    override fun save(payment: Payment): Payment =
        paymentRepository.save(payment.toEntity()).toPayment()


    override fun updateToApprovedStatus(paymentId: String) {
        val paymentEntity = paymentRepository.findByPaymentId(paymentId)
        paymentEntity?.let {
            val payment = it.toPayment()
            payment.updateToApprovedStatus()
            paymentRepository.save(payment.toEntity())
        }
    }

    override fun updateToRefusedStatus(paymentId: String) {
        val paymentEntity = paymentRepository.findByPaymentId(paymentId)
        paymentEntity?.let {
            val payment = it.toPayment()
            payment.updateToRefusedStatus()
            paymentRepository.save(payment.toEntity())
        }    }

    override fun findByStatus(status: PaymentStatus): List<Payment> =
        paymentRepository.findByStatus(status.name).mapNotNull { it?.toPayment() }

}