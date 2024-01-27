package br.com.fiap.adapters.inbound.request

data class Pedido(
        val id: Long,
        val codigoPedido: String,
        val valorTotal: Double,
        val status: String,
        val cliente: Cliente
)

