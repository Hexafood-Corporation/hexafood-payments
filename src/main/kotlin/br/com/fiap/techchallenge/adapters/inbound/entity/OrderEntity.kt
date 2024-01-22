package br.com.fiap.techchallenge.adapters.inbound.entity

import br.com.fiap.techchallenge.application.core.domain.Order
import br.com.fiap.techchallenge.application.core.enums.OrderStatus
import br.com.fiap.techchallenge.application.core.enums.PaymentMethod
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity(name = "orders")
data class OrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var orderId: String? = null,

    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: ClientEntity? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    val products: List<ProductEntity>,

    val status: String = OrderStatus.RECEIVED.toString(),

    @CreationTimestamp
    val statusUpdatedAt: LocalDateTime? = null,

    var total: BigDecimal? = null,
    val additionalNotes: String? = null,

    val paymentMethod: String,

    @CreationTimestamp
    val orderDate: LocalDateTime? = null
) {
    @PrePersist
    fun generate() {
        orderId = UUID.randomUUID().toString()
        total = products.map { it.value }.reduce { acc, bigDecimal -> acc + bigDecimal }
    }

    fun toOrder(): Order {
        return Order(
            id!!,
            orderId,
            client?.toClient(),
            products.map { it.toProduct() },
            OrderStatus.valueOf(status),
            statusUpdatedAt!!,
            total,
            additionalNotes,
            PaymentMethod.valueOf(paymentMethod),
            orderDate!!
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderEntity

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
        result = 31 * result + (statusUpdatedAt?.hashCode() ?: 0)
        result = 31 * result + total.hashCode()
        result = 31 * result + (additionalNotes?.hashCode() ?: 0)
        result = 31 * result + paymentMethod.hashCode()
        result = 31 * result + (orderDate?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "OrderEntity(id=$id, orderId='$orderId', client=$client, products=$products, status=$status, statusUpdatedAt=$statusUpdatedAt, total=$total, additionalNotes=$additionalNotes, paymentMethod='$paymentMethod', orderDate=$orderDate)"
    }


}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        orderId = orderId,
        client = client?.toEntity(),
        products = products.map { it.toEntity() },
        status = status.toString(),
        statusUpdatedAt = statusUpdatedAt,
        total = total,
        additionalNotes = additionalNotes,
        paymentMethod = paymentMethod.toString(),
        orderDate = orderDate
    )
}