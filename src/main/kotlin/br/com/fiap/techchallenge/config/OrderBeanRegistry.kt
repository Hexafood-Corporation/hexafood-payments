package br.com.fiap.techchallenge.config

import br.com.fiap.techchallenge.application.core.usecase.CreateOrderUseCaseImpl
import br.com.fiap.techchallenge.application.core.usecase.FindAllOrdersUseCaseImpl
import br.com.fiap.techchallenge.application.core.usecase.FindOrderByCodeUseCaseImpl
import br.com.fiap.techchallenge.application.ports.`in`.ICreateOrderUseCase
import br.com.fiap.techchallenge.application.ports.`in`.IFindAllOrdersUseCase
import br.com.fiap.techchallenge.application.ports.`in`.IFindOrderByCodeUseCase
import br.com.fiap.techchallenge.application.ports.out.IOrderPersistence
import br.com.fiap.techchallenge.application.ports.out.IPaymentPersistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderBeanRegistry {

    @Bean
    fun findAllOrdersUseCase(orderPersistence: IOrderPersistence): IFindAllOrdersUseCase {
        return FindAllOrdersUseCaseImpl(orderPersistence)
    }

    @Bean
    fun createOrderUseCase(orderPersistence: IOrderPersistence, paymentPersistence: IPaymentPersistence)
    : ICreateOrderUseCase {
        return CreateOrderUseCaseImpl(orderPersistence, paymentPersistence)
    }

    @Bean
    fun findOrderByCodeUseCase(orderPersistence: IOrderPersistence): IFindOrderByCodeUseCase {
        return FindOrderByCodeUseCaseImpl(orderPersistence)
    }

}