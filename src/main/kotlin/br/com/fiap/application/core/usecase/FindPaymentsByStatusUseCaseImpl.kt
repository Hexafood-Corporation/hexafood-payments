package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentStatus
import br.com.fiap.application.core.exception.PaymentNotFoundException
import br.com.fiap.application.ports.`in`.IFindAllPaymentsUseCase
import br.com.fiap.application.ports.`in`.IFindPaymentsByStatusUseCase
import br.com.fiap.application.ports.out.IPaymentPersistence

class FindPaymentsByStatusUseCaseImpl(
    private val paymentPersistence: IPaymentPersistence
) : IFindPaymentsByStatusUseCase {

    override fun findByStatus(status: PaymentStatus): List<Payment> =
        paymentPersistence.findByStatus(status)
}