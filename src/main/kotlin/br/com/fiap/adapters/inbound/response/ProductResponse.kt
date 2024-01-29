package br.com.fiap.adapters.inbound.response

import java.math.BigDecimal

data class ProductResponse(
    val title: String = "",
    val sku: String,
    val productType: String = "",
    val description: String = "",
    val value: BigDecimal,
    val isActive: Boolean = true
)
