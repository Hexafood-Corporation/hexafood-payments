package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Client

fun interface IIdentifyClientByCPFUseCase {
    fun identifyClientByCPF(cpf: String): Client
}