package br.com.fiap.techchallenge.application.core.domain

import br.com.fiap.techchallenge.application.core.enums.OrderStatus
import br.com.fiap.techchallenge.application.core.enums.PaymentMethod
import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
    val id: Int? = null,
    val orderId: String? = null,
    val client: Client? = null,
    val products: List<Product>,
    var status: OrderStatus? = null,
    var statusUpdatedAt: LocalDateTime? = null,
    val total: BigDecimal? = null,
    val additionalNotes: String? = null,
    val paymentMethod: PaymentMethod,
    val orderDate: LocalDateTime? = null
) {
    fun isValid(): Boolean {
        return products.isNotEmpty()

    }

    fun updateToInPreparationStatus() {
        status = OrderStatus.IN_PREPARATION
        statusUpdatedAt = LocalDateTime.now()
    }

    fun updateToReadyStatus() {
        status = OrderStatus.READY
        statusUpdatedAt = LocalDateTime.now()
    }

    fun updateToFinishedStatus() {
        status = OrderStatus.FINISHED
        statusUpdatedAt = LocalDateTime.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (id != other.id) return false
        if (orderId != other.orderId) return false
        if (client != other.client) return false
        if (products != other.products) return false
        if (status != other.status) return false
        if (statusUpdatedAt != other.statusUpdatedAt) return false
        if (total != other.total) return false
        if (additionalNotes != other.additionalNotes) return false
        if (paymentMethod != other.paymentMethod) return false
        return orderDate == other.orderDate
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + orderId.hashCode()
        result = 31 * result + client.hashCode()
        result = 31 * result + products.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + statusUpdatedAt.hashCode()
        result = 31 * result + total.hashCode()
        result = 31 * result + (additionalNotes?.hashCode() ?: 0)
        result = 31 * result + paymentMethod.hashCode()
        result = 31 * result + orderDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Order(id=$id, orderId='$orderId', client=$client, products=$products, status=$status, statusUpdatedAt=$statusUpdatedAt, total=$total, additionalNotes=$additionalNotes, paymentMethod='$paymentMethod', orderDate=$orderDate)"
    }

}