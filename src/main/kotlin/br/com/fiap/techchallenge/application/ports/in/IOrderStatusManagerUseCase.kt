package br.com.fiap.techchallenge.application.ports.`in`

interface IOrderStatusManagerUseCase {

    fun updateToInPreparationStatus(orderId: String)
    fun updateToReadyStatus(orderId: String)
    fun updateToFinishedStatus(orderId: String)

}