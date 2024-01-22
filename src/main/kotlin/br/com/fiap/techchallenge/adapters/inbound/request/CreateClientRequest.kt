package br.com.fiap.techchallenge.adapters.inbound.request

import br.com.fiap.techchallenge.application.core.domain.Client

data class CreateClientRequest(
    val name: String,
    val cpf: String,
    val email: String,
    val phone: String
) {
    fun toClient() = Client(name = name, cpf = cpf, email = email, phone = phone)
}