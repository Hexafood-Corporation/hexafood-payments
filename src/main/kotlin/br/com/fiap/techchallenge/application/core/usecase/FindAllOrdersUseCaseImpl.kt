package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.domain.Order
import br.com.fiap.techchallenge.application.ports.`in`.IFindAllOrdersUseCase
import br.com.fiap.techchallenge.application.ports.out.IOrderPersistence

class FindAllOrdersUseCaseImpl(
    private val orderPersistence: IOrderPersistence
) : IFindAllOrdersUseCase {

    override fun findAll(): List<Order> =
        orderPersistence.findAll()

}