package br.com.fiap.techchallenge.adapters.inbound.response

import br.com.fiap.techchallenge.application.core.domain.Client

data class ClientResponse(
    val code: String? = null,
    val name: String = "",
    val cpf: String = "",
    val email: String = "",
    val phone: String = "",
    val active: Boolean = true
)

fun Client.toClientResponse() = ClientResponse(
    code = code,
    name = name,
    cpf = cpf,
    email = email,
    phone = phone,
    active = active!!
)