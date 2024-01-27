package br.com.fiap.config

import br.com.fiap.application.core.usecase.*
import br.com.fiap.application.ports.`in`.*
import br.com.fiap.application.ports.out.IPaymentPersistence
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class PaymentBeanRegistry {
    @Bean
    @Primary
    fun amazonSQSAsync(): AmazonSQSAsync? {
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials("dummy", "dummy")
                )
            ) // access key and secret key
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
            .build()
    }
    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate? {
        return QueueMessagingTemplate(amazonSQSAsync())
    }

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