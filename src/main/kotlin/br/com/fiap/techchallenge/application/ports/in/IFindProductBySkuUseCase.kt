package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Product

fun interface IFindProductBySkuUseCase {
    fun findBySku(sku: String): Product
}