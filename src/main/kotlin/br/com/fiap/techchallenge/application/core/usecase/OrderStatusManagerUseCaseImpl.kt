package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.ports.`in`.IOrderStatusManagerUseCase
import br.com.fiap.techchallenge.application.ports.out.IOrderPersistence

class OrderStatusManagerUseCaseImpl(
    private val orderPersistence: IOrderPersistence
) : IOrderStatusManagerUseCase {

    override fun updateToInPreparationStatus(orderId: String)  =
        orderPersistence.updateToInPreparationStatus(orderId)


    override fun updateToReadyStatus(orderId: String) =
        orderPersistence.updateToReadyStatus(orderId)


    override fun updateToFinishedStatus(orderId: String) =
        orderPersistence.updateToFinishedStatus(orderId)

}