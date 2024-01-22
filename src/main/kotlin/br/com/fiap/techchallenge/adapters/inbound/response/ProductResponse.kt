package br.com.fiap.techchallenge.adapters.inbound.response

import br.com.fiap.techchallenge.application.core.domain.Product
import java.math.BigDecimal

data class ProductResponse(
    val title: String = "",
    val sku: String,
    val productType: String = "",
    val description: String = "",
    val value: BigDecimal,
    val isActive: Boolean = true
)

fun Product.toProductResponse() = ProductResponse(
    title = title,
    sku = sku,
    productType = productType.toString(),
    description = description,
    value = value,
    isActive = isActive!!
)