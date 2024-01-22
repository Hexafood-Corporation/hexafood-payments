package br.com.fiap.techchallenge.config

import br.com.fiap.techchallenge.application.core.usecase.*
import br.com.fiap.techchallenge.application.ports.`in`.*
import br.com.fiap.techchallenge.application.ports.out.IClientPersistence
import br.com.fiap.techchallenge.application.ports.out.IOrderPersistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientBeanRegistry {

    @Bean
    fun createClientUseCase(clientPersistence: IClientPersistence): ICreateClientUseCase {
        return CreateClientUseCaseImpl(clientPersistence)
    }

    @Bean
    fun findClientByCodeUseCase(clientPersistence: IClientPersistence): IFindClientByCodeUseCase {
        return FindClientByCodeUseCaseImpl(clientPersistence)
    }

    @Bean
    fun findAllClientsUseCase(clientPersistence: IClientPersistence): IFindAllClientsUseCase {
        return FindAllClientsUseCaseImpl(clientPersistence)
    }

    @Bean
    fun activeClientUseCase(clientPersistence: IClientPersistence): IActivationClientUseCase {
        return ActivationClientUseCaseImpl(clientPersistence)
    }

    @Bean
    fun identifyClientUseCase(clientPersistence: IClientPersistence): IIdentifyClientByCPFUseCase {
        return IdentifyClientByCPFUseCaseImpl(clientPersistence)
    }

    @Bean
    fun orderStatusManagerUseCase(orderPersistence: IOrderPersistence): IOrderStatusManagerUseCase {
        return OrderStatusManagerUseCaseImpl(orderPersistence)
    }

}