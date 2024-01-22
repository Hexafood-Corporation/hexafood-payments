package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Product
import br.com.fiap.techchallenge.application.core.enums.ProductType

fun interface IFindProductsByProductTypeUseCase {
    fun findAllProductTypes(product: ProductType): List<Product>
}