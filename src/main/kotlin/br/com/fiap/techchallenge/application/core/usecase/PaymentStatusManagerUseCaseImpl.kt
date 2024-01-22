package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.ports.`in`.IPaymentStatusManagerUseCase
import br.com.fiap.techchallenge.application.ports.out.IPaymentPersistence

class PaymentStatusManagerUseCaseImpl(
    private val paymentPersistence: IPaymentPersistence
) : IPaymentStatusManagerUseCase {
    override fun updateToApprovedStatus(paymentId: String) =
        paymentPersistence.updateToApprovedStatus(paymentId)

    override fun updateToRefusedStatus(paymentId: String) =
        paymentPersistence.updateToRefusedStatus(paymentId)
}