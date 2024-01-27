package br.com.fiap.application.ports.`in`

interface IPaymentStatusManagerUseCase {
    fun updateToApprovedStatus(paymentId: String)
    fun updateToRefusedStatus(paymentId: String)
}