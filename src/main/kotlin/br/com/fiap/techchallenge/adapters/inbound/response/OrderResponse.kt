package br.com.fiap.techchallenge.adapters.inbound.response

import br.com.fiap.techchallenge.application.core.domain.Order
import br.com.fiap.techchallenge.application.core.enums.OrderStatus
import br.com.fiap.techchallenge.application.core.enums.PaymentMethod
import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderResponse(
    var orderId: String,
    val client: ClientResponse? = null,
    val products: List<ProductResponse>,
    val status: OrderStatus = OrderStatus.RECEIVED,
    val statusUpdatedAt: LocalDateTime? = null,
    val total: BigDecimal,
    val additionalNotes: String? = null,
    val paymentMethod: PaymentMethod,
    val orderDate: LocalDateTime? = null
)

data class OrderCreateResponse(
    var orderId: String
)
fun Order.toOrderResponse() = OrderResponse(
    orderId = orderId!!,
    client = client?.toClientResponse(),
    products = products.map { it.toProductResponse() },
    status = status!!,
    statusUpdatedAt = statusUpdatedAt,
    total = total!!,
    additionalNotes = additionalNotes,
    paymentMethod = paymentMethod,
    orderDate = orderDate
)