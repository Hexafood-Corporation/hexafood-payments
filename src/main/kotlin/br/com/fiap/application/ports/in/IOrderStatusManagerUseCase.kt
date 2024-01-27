package br.com.fiap.application.ports.`in`

interface IOrderStatusManagerUseCase {

    fun updateToInPreparationStatus(orderId: String)
    fun updateToReadyStatus(orderId: String)
    fun updateToFinishedStatus(orderId: String)

}