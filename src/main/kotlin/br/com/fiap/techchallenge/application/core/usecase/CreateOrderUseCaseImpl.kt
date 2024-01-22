package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.domain.Order
import br.com.fiap.techchallenge.application.core.domain.Payment
import br.com.fiap.techchallenge.application.core.exception.InvalidOrderException
import br.com.fiap.techchallenge.application.ports.`in`.ICreateOrderUseCase
import br.com.fiap.techchallenge.application.ports.out.IOrderPersistence
import br.com.fiap.techchallenge.application.ports.out.IPaymentPersistence

class CreateOrderUseCaseImpl(
    private val orderPersistence: IOrderPersistence,
    private val paymentPersistence: IPaymentPersistence
) : ICreateOrderUseCase {

    override fun save(order: Order): Order {
        require(order.isValid()) {
            throw InvalidOrderException("Order is invalid, it needs at least one product!")
        }

        val response = orderPersistence.save(order)
//        paymentPersistence.save(payment)

        return response
    }
}