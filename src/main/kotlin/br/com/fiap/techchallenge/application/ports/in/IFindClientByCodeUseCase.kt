package br.com.fiap.techchallenge.application.ports.`in`

import br.com.fiap.techchallenge.application.core.domain.Client

fun interface IFindClientByCodeUseCase {

    fun findByCode (code: String): Client
}