package br.com.fiap.config

import br.com.fiap.application.core.usecase.*
import br.com.fiap.application.ports.`in`.*
import br.com.fiap.application.ports.out.IPaymentPersistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentBeanRegistry {

    @Bean
    fun findAllPaymentssUseCase(paymentPersistence: IPaymentPersistence): IFindAllPaymentsUseCase {
        return FindAllPaymentsUseCaseImpl(paymentPersistence)
    }

    @Bean
    fun createPaymentUseCase(paymentPersistence: IPaymentPersistence): ICreatePaymentUseCase {
        return CreatePaymentUseCaseImpl(paymentPersistence)
    }

    @Bean
    fun findPaymentByIdUseCase(paymentPersistence: IPaymentPersistence): IFindPaymentByIdUseCase {
        return FindPaymentByIdUseCaseImpl(paymentPersistence)
    }

    @Bean
    fun paymentStatusManagerUseCase(paymentPersistence: IPaymentPersistence): IPaymentStatusManagerUseCase {
        return PaymentStatusManagerUseCaseImpl(paymentPersistence)
    }
}