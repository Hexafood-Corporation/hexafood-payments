package br.com.fiap.techchallenge.adapters.inbound.controller

import br.com.fiap.techchallenge.adapters.inbound.request.CreateOrderRequest
import br.com.fiap.techchallenge.adapters.inbound.response.OrderCreateResponse
import br.com.fiap.techchallenge.adapters.inbound.response.OrderResponse
import br.com.fiap.techchallenge.adapters.inbound.response.toOrderResponse
import br.com.fiap.techchallenge.application.core.domain.Payment
import br.com.fiap.techchallenge.application.core.enums.OrderStatus
import br.com.fiap.techchallenge.application.ports.`in`.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class OrderController(
    private val findAllOrdersUseCase: IFindAllOrdersUseCase,
    private val createOrdersUseCase: ICreateOrderUseCase,
    private val findClientByCodeUseCase: IFindClientByCodeUseCase,
    private val findProductBySkuUseCase: IFindProductBySkuUseCase,
    private val orderStatusManagerUseCase: IOrderStatusManagerUseCase,
    private val findOrderByCodeUseCase: IFindOrderByCodeUseCase
) {

    @GetMapping("/v1/orders")
    fun findAllOrders(): ResponseEntity<List<OrderResponse?>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                findAllOrdersUseCase.findAll().map { it.toOrderResponse() }
            )

    @GetMapping("/v1/orders/checkout")
    fun checkout(): ResponseEntity<List<OrderResponse?>> {
        val orders = findAllOrdersUseCase.findAll().filter { it.status != OrderStatus.FINISHED }.sortedBy { it.status?.index }
        val orderResponses = orders.map { it.toOrderResponse() }
        return ResponseEntity.status(HttpStatus.OK).body(orderResponses)
    }

    @PostMapping("/v1/orders")
    fun create(@RequestBody request: CreateOrderRequest): ResponseEntity<OrderCreateResponse> {
        val client = request.clientCode?.let { findClientByCodeUseCase.findByCode(it) }
        val products = request.productsSku.map { findProductBySkuUseCase.findBySku(it) }
        val order = createOrdersUseCase.save(request.toOrder(client = client, products = products)).toOrderResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderCreateResponse(order.orderId))
    }

    @PutMapping("/v1/orders/{orderId}/in-preparation")
    fun updateToInPreparationStatus(@PathVariable orderId: String): ResponseEntity<Unit> {
        orderStatusManagerUseCase.updateToInPreparationStatus(orderId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PutMapping("/v1/orders/{orderId}/ready")
    fun updateToReadyStatus(@PathVariable orderId: String): ResponseEntity<Unit> {
        orderStatusManagerUseCase.updateToReadyStatus(orderId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PutMapping("/v1/orders/{orderId}/finished")
    fun updateToCompletedStatus(@PathVariable orderId: String): ResponseEntity<Unit> {
        orderStatusManagerUseCase.updateToFinishedStatus(orderId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @GetMapping("/v1/orders/{orderId}")
    fun findByCode(@PathVariable orderId: String): ResponseEntity<OrderResponse> {
        val order = findOrderByCodeUseCase.findByOrderId(orderId).toOrderResponse()
        return ResponseEntity.ok(order)
    }

}