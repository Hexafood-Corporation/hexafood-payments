package br.com.fiap.application.ports.`in`

import br.com.fiap.application.core.domain.Payment

interface IPaymentStatusManagerUseCase {
    fun updateToApprovedStatus(paymentId: String)
    fun updateToRefusedStatus(paymentId: String)
}