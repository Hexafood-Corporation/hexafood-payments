package br.com.fiap.adapters.inbound.entity

import br.com.fiap.application.core.domain.Payment
import br.com.fiap.application.core.enums.PaymentMethod
import br.com.fiap.application.core.enums.PaymentStatus
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

    @Column(name="order_id", nullable = false)
    val order: String,

    val status: String = PaymentStatus.IN_PROCESS.toString(),

    @Column(nullable = false)
    val clientId: Long,

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
            order,
            PaymentStatus.valueOf(status),
            statusUpdatedAt = statusUpdatedAt,
            clientId = clientId,
            paymentMethod = PaymentMethod.valueOf(paymentMethod),

        )
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaymentEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

}

fun Payment.toEntity(): PaymentEntity {
    return PaymentEntity(
        id = id,
        paymentId = paymentId,
        order = order!!,
        clientId = clientId!!,
        statusUpdatedAt = statusUpdatedAt,
        status = status.toString(),
        paymentMethod = paymentMethod.toString()
    )
}