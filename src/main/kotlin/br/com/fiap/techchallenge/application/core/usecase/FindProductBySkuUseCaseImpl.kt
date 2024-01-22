package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.exception.ProductNotFoundException
import br.com.fiap.techchallenge.application.ports.`in`.IFindProductBySkuUseCase
import br.com.fiap.techchallenge.application.ports.out.IProductPersistence

class FindProductBySkuUseCaseImpl(
    private val productPersistence: IProductPersistence
) : IFindProductBySkuUseCase {

    override fun findBySku(sku: String) =
        productPersistence.findBySku(sku) ?:
        throw ProductNotFoundException("Sku's $sku Product not found.")
}