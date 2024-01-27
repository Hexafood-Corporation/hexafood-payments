package br.com.fiap.adapters.inbound.controller

import br.com.fiap.adapters.inbound.request.CreatePaymentRequest
import br.com.fiap.adapters.inbound.request.CreatePaymentResponse
import br.com.fiap.adapters.inbound.response.PaymentResponse
import br.com.fiap.adapters.inbound.response.toPaymentResponse
import br.com.fiap.application.ports.`in`.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PaymentController(
    private val paymentaStatus: IPaymentStatusManagerUseCase,
    private val createPaymentUseCase: ICreatePaymentUseCase,
    private val findAllPayments: IFindAllPaymentsUseCase
) {

    @GetMapping("/v1/payments")
    fun findAllPayments(): ResponseEntity<List<PaymentResponse?>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                findAllPayments.findAll().map { it.toPaymentResponse() }
            )

    @PostMapping("/v1/payments")
    fun create(@RequestBody request: CreatePaymentRequest): ResponseEntity<CreatePaymentResponse> {
        val order = request.orderId

        val payment = createPaymentUseCase.save(request.toPayment(order!!)).toPaymentResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatePaymentResponse(payment.paymentId))
    }

    @PutMapping("/v1/payments/{paymentId}/approved")
    fun updateToApprovedStatus(@PathVariable paymentId: String): ResponseEntity<Unit> {
        paymentaStatus.updateToApprovedStatus(paymentId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PutMapping("/v1/payments/{paymentId}/refused")
    fun updateToRefusedStatus(@PathVariable paymentId: String): ResponseEntity<Unit> {
        paymentaStatus.updateToRefusedStatus(paymentId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}