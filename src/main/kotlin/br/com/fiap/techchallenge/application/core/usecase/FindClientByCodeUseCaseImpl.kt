package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.domain.Client
import br.com.fiap.techchallenge.application.core.exception.ClientNotFoundException
import br.com.fiap.techchallenge.application.ports.`in`.IFindClientByCodeUseCase
import br.com.fiap.techchallenge.application.ports.out.IClientPersistence

class FindClientByCodeUseCaseImpl(
    private val clientPersistence: IClientPersistence
) : IFindClientByCodeUseCase {

    override fun findByCode(code: String): Client {
        return clientPersistence.findByCode(code) ?:
            throw ClientNotFoundException("Client with code: $code not found")
    }
}