package br.com.fiap.techchallenge.adapters.outbound.service

import br.com.fiap.techchallenge.adapters.inbound.entity.toEntity
import br.com.fiap.techchallenge.adapters.outbound.repository.IProductRepository
import br.com.fiap.techchallenge.application.core.domain.Product
import br.com.fiap.techchallenge.application.core.enums.ProductType
import br.com.fiap.techchallenge.application.ports.out.IProductPersistence
import org.springframework.stereotype.Service

@Service
class ProductPersistenceImpl(
    private val productRepository: IProductRepository
) : IProductPersistence {
    override fun save(product: Product) =
        productRepository.save(product.toEntity()).toProduct()

    override fun findBySku(sku: String) =
        productRepository.findBySku(sku)?.toProduct()

    override fun findByProductType(productType: ProductType): List<Product> =
        productRepository.findByProductType(productType.toString()).map { it.toProduct() }

    override fun enabled(sku: String) {
        val product = productRepository.findBySku(sku)
        product?.let {
            it.enabled()
            productRepository.save(it)
        }
    }
    override fun disabled(sku: String) {
        val product = productRepository.findBySku(sku)
        product?.let {
            it.desabled()
            productRepository.save(it)
        }
    }
}

