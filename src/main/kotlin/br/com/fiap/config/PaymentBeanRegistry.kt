package br.com.fiap.config

import br.com.fiap.application.core.usecase.*
import br.com.fiap.application.ports.`in`.*
import br.com.fiap.application.ports.out.IPaymentPersistence
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class PaymentBeanRegistry {
    @Value("\${AWS_ENDPOINT}")
    private lateinit var AWS_ENDPOINT: String

    @Value("\${AWS_REGION}")
    private lateinit var AWS_REGION: String

    @Value("\${AWS_ACCESS_KEY}")
    private lateinit var AWS_ACCESS_KEY: String

    @Value("\${AWS_SECRET_KEY}")
    private lateinit var AWS_SECRET_KEY: String

    @Bean
    @Primary
    fun amazonSQSAsync(): AmazonSQSAsync? {
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)
                )
            ) // access key and secret key
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    AWS_ENDPOINT, AWS_REGION))
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
    fun findPaymentsByStatus(paymentPersistence: IPaymentPersistence): IFindPaymentsByStatusUseCase {
        return FindPaymentsByStatusUseCaseImpl(paymentPersistence)
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