package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.domain.Payment
import br.com.fiap.techchallenge.application.ports.`in`.IFindAllPaymentsUseCase
import br.com.fiap.techchallenge.application.ports.out.IPaymentPersistence

class FindAllPaymentsUseCaseImpl(
    private val paymentPersistence: IPaymentPersistence
) : IFindAllPaymentsUseCase {

    override fun findAll(): List<Payment> =
        paymentPersistence.findAll()

}