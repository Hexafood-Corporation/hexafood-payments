package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.domain.Client
import br.com.fiap.techchallenge.application.core.exception.InvalidClientException
import br.com.fiap.techchallenge.application.ports.`in`.ICreateClientUseCase
import br.com.fiap.techchallenge.application.ports.out.IClientPersistence

class CreateClientUseCaseImpl(
    private val clientPersistence: IClientPersistence
) : ICreateClientUseCase {
    override fun save(client: Client): Client {
        if (!client.isValid()) {
            throw InvalidClientException("Client is invalid")
        }
        return clientPersistence.save(client)
    }

}