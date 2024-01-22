package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.exception.OrderNotFoundException
import br.com.fiap.techchallenge.application.ports.`in`.IFindOrderByCodeUseCase
import br.com.fiap.techchallenge.application.ports.out.IOrderPersistence

class FindOrderByCodeUseCaseImpl(
    private val orderPersistence: IOrderPersistence
) : IFindOrderByCodeUseCase {

    override fun findByOrderId(orderId: String) =
        orderPersistence.findByCode(orderId) ?:
            throw OrderNotFoundException("Order with code: $orderId not found")
}