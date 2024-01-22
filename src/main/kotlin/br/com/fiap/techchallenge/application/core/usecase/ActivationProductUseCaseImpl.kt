package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.ports.`in`.IActivationProductUseCase
import br.com.fiap.techchallenge.application.ports.out.IProductPersistence

class ActivationProductUseCaseImpl(
    private val productPersistence: IProductPersistence
) : IActivationProductUseCase {

    override fun enabled(sku: String) =
        productPersistence.enabled(sku)

    override fun disabled(sku: String) =
        productPersistence.disabled(sku)

}