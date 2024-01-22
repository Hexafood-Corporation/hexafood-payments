package br.com.fiap.techchallenge.config

import br.com.fiap.techchallenge.application.core.usecase.ActivationProductUseCaseImpl
import br.com.fiap.techchallenge.application.core.usecase.CreateProductUseCaseImpl
import br.com.fiap.techchallenge.application.core.usecase.FindProductBySkuUseCaseImpl
import br.com.fiap.techchallenge.application.core.usecase.FindProductsByProductTypeUseCaseImpl
import br.com.fiap.techchallenge.application.ports.out.IProductPersistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductBeanRegistry {

    @Bean
    fun createProductUseCase(productPersistence: IProductPersistence) =
        CreateProductUseCaseImpl(productPersistence)


    @Bean
    fun findBySku(productPersistence: IProductPersistence) =
        FindProductBySkuUseCaseImpl(productPersistence)

    @Bean
    fun findByProductType(productPersistence: IProductPersistence) =
        FindProductsByProductTypeUseCaseImpl(productPersistence)

    @Bean
    fun status(productPersistence: IProductPersistence) =
        ActivationProductUseCaseImpl(productPersistence)
}