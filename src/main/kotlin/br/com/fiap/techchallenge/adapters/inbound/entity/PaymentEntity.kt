package br.com.fiap.techchallenge.adapters.inbound.entity

import br.com.fiap.techchallenge.application.core.domain.Payment
import br.com.fiap.techchallenge.application.core.enums.PaymentMethod
import br.com.fiap.techchallenge.application.core.enums.PaymentStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity(name = "payments")
data class PaymentEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var paymentId: String? = null,

    @OneToOne
    @JoinColumn(name = "order_id")
    val order: OrderEntity? = null,

    val status: String = PaymentStatus.IN_PROCESS.toString(),

    @CreationTimestamp
    val statusUpdatedAt: LocalDateTime? = null,

    val paymentMethod: String,

    ) {
    @PrePersist
    fun generate() {
        paymentId = UUID.randomUUID().toString()
    }

    fun toPayment(): Payment {
        return Payment(
            id!!,
            paymentId,
            order?.toOrder(),
            PaymentStatus.valueOf(status),
            statusUpdatedAt = statusUpdatedAt,
            PaymentMethod.valueOf(paymentMethod),
        )
    }


    final override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaymentEntity

        if (id != other.id) return false
        if (paymentId != other.paymentId) return false
        if (order != other.order) return false
        if (status != other.status) return false
        if (statusUpdatedAt != other.statusUpdatedAt) return false
        return paymentMethod == other.paymentMethod
    }

}

fun Payment.toEntity(): PaymentEntity {
    return PaymentEntity(
        id = id,
        paymentId = paymentId,
        order = order?.toEntity(),
        statusUpdatedAt = statusUpdatedAt,
        status = status.toString(),
        paymentMethod = paymentMethod.toString()
    )
}