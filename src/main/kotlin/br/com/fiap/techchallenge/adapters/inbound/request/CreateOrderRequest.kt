package br.com.fiap.techchallenge.adapters.inbound.request

import br.com.fiap.techchallenge.application.core.domain.Client
import br.com.fiap.techchallenge.application.core.domain.Order
import br.com.fiap.techchallenge.application.core.domain.Payment
import br.com.fiap.techchallenge.application.core.domain.Product
import br.com.fiap.techchallenge.application.core.enums.OrderStatus
import br.com.fiap.techchallenge.application.core.enums.PaymentMethod


data class CreateOrderRequest(

    val clientCode: String? = null,
    val productsSku: List<String>,
    val additionalNotes: String? = null,
    val paymentMethod: PaymentMethod,
) {

    fun toOrder(client: Client?, products: List<Product>): Order {
        return Order(
            client = client,
            products = products,
            status = OrderStatus.RECEIVED,
            additionalNotes = additionalNotes,
            paymentMethod = paymentMethod
        )
    }
}