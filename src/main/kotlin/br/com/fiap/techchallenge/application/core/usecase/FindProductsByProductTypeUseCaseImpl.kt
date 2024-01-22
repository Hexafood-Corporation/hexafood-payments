package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.enums.ProductType
import br.com.fiap.techchallenge.application.ports.`in`.IFindProductsByProductTypeUseCase
import br.com.fiap.techchallenge.application.ports.out.IProductPersistence

class FindProductsByProductTypeUseCaseImpl(
    private val productPersistence: IProductPersistence
) : IFindProductsByProductTypeUseCase {

    override fun findAllProductTypes(product: ProductType) =
        productPersistence.findByProductType(product)
}