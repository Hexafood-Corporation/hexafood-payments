package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.ports.`in`.IFindAllPaymentsUseCase
import br.com.fiap.application.ports.out.IPaymentPersistence

class FindAllPaymentsUseCaseImpl(
    private val paymentPersistence: IPaymentPersistence
) : IFindAllPaymentsUseCase {

    override fun findAll(): List<Payment> =
        paymentPersistence.findAll()

}