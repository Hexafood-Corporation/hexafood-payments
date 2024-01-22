package br.com.fiap.techchallenge.adapters.inbound.entity

import br.com.fiap.techchallenge.application.core.domain.Product
import br.com.fiap.techchallenge.application.core.enums.ProductType
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "products")
data class ProductEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var sku: String,
    val title: String,
    val description: String,
    val productType: String,
    val value: BigDecimal,
    private var isActive: Boolean = true,

    @CreationTimestamp
    val creationDate: OffsetDateTime? = null
) {
    @PrePersist
    fun generateCode() {
        sku = UUID.randomUUID().toString()
    }

    fun toProduct(): Product {
        return Product(id!!, sku, title, description, ProductType.valueOf(productType), value,isActive)
    }

    fun enabled() {
        isActive = true
    }

    fun desabled() {
        isActive = false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ProductEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        sku = sku,
        isActive = isActive!!,
        productType = productType.toString(),
        title = title,
        value = value,
        description = description)
}


