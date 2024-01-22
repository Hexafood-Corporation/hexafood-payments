package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.domain.Product
import br.com.fiap.techchallenge.application.core.exception.InvalidProductException
import br.com.fiap.techchallenge.application.ports.`in`.ICreateProductUseCase
import br.com.fiap.techchallenge.application.ports.out.IProductPersistence

class CreateProductUseCaseImpl(
    private val productPersistence: IProductPersistence
) : ICreateProductUseCase {
    override fun save(product: Product): Product {
        require(product.isValid()) {
            throw InvalidProductException("Product is invalid, it needs a title!")
        }

        return productPersistence.save(product)
    }

}