package br.com.fiap.techchallenge.application.core.domain

import br.com.fiap.techchallenge.application.core.enums.ProductType
import java.math.BigDecimal

data class Product(
    val id: Int? = null,
    val sku: String,
    val title: String,
    val description: String,
    val productType: ProductType,
    val value: BigDecimal,
    val isActive: Boolean? = true
) {
    fun isValid(): Boolean {
        return title.isNotBlank()
    }

    override fun toString(): String {
        return "Product(id=$id, sku=$sku, title='$title', description ='$description', productType=$productType, isActive=$isActive, value=$value)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (sku != other.sku) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (productType != other.productType) return false
        return isActive == other.isActive
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (sku.hashCode() ?: 0)
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + productType.hashCode()
        result = 31 * result + (isActive?.hashCode() ?: 0)
        return result
    }


}


