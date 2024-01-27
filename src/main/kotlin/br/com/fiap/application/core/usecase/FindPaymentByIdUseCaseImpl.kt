package br.com.fiap.application.core.usecase

import br.com.fiap.application.core.exception.PaymentNotFoundException
import br.com.fiap.application.ports.`in`.IFindPaymentByIdUseCase
import br.com.fiap.application.ports.out.IPaymentPersistence

class FindPaymentByIdUseCaseImpl(
    private val paymentPersistence: IPaymentPersistence
) : IFindPaymentByIdUseCase {

    override fun findByPaymentId(paymentId: String) =
        paymentPersistence.findByPaymentId(paymentId) ?:
            throw PaymentNotFoundException("Payment with code: $paymentId not found")
}