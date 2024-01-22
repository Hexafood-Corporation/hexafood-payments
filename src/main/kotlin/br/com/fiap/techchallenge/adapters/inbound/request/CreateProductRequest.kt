package br.com.fiap.techchallenge.adapters.inbound.request

import br.com.fiap.techchallenge.application.core.domain.Product
import br.com.fiap.techchallenge.application.core.enums.ProductType
import java.math.BigDecimal

data class CreateProductRequest(
    val title: String,
    val sku: String,
    val productType: ProductType,
    val description: String,
    val value: BigDecimal
) {
    fun toProduct() = Product(title = title, productType = productType, description = description, sku = sku, value=value)
}