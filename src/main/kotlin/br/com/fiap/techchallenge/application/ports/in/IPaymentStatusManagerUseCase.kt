package br.com.fiap.techchallenge.application.ports.`in`

interface IPaymentStatusManagerUseCase {
    fun updateToApprovedStatus(paymentId: String)
    fun updateToRefusedStatus(paymentId: String)
}