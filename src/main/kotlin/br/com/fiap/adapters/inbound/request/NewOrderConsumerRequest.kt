package br.com.fiap.adapters.inbound.request

data class Pedido(
        val id: Long,
        val codigoPedido: String,
        val valorTotal: Double,
        val status: String,
        val itens: List<Item>,
        val cliente: Cliente
)

data class Item(
        val id: Long,
        val quantidade: Int,
        val valor: Double,
        val produto: Produto
)

data class Produto(
        val id: Long,
        val nome: String
)

data class Cliente(
        val id: Long,
        val nome: String,
        val cpf: String
)
