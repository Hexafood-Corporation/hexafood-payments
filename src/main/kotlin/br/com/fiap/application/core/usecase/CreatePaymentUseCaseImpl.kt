package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.ports.`in`.ICreatePaymentUseCase
import br.com.fiap.application.ports.out.IPaymentPersistence

class CreatePaymentUseCaseImpl(
    private val paymentPersistence: IPaymentPersistence
) : ICreatePaymentUseCase {

    override fun save(payment: Payment): Payment {
        return paymentPersistence.save(payment)
    }
}