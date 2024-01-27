package br.com.fiap.adapters.inbound.consumer

import br.com.fiap.adapters.inbound.request.CreatePaymentRequest
import br.com.fiap.adapters.inbound.request.Pedido
import br.com.fiap.application.ports.`in`.ICreatePaymentUseCase
import br.com.fiap.application.ports.`in`.IFindAllPaymentsUseCase
import br.com.fiap.application.ports.`in`.IPaymentStatusManagerUseCase
import org.springframework.stereotype.Service
import io.awspring.cloud.sqs.annotation.SqsListener

@Service
class PaymentConsumer(
    private val createPaymentUseCase: ICreatePaymentUseCase,
) {

    @SqsListener("novo_pedido")
    fun onMessage(order: CreatePaymentRequest) {
        createPaymentUseCase.save(order.toPayment())
    }
}