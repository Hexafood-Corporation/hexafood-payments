package br.com.fiap.techchallenge.application.ports.out

import br.com.fiap.techchallenge.application.core.domain.Product
import br.com.fiap.techchallenge.application.core.enums.ProductType

interface IProductPersistence {
    fun save(product: Product): Product
    fun findBySku(sku: String): Product?
    fun findByProductType(productType: ProductType): List<Product>
    fun enabled(sku: String)
    fun disabled(sku: String)

}