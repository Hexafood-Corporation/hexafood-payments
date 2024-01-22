package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.core.domain.Client
import br.com.fiap.techchallenge.application.ports.`in`.IFindAllClientsUseCase
import br.com.fiap.techchallenge.application.ports.out.IClientPersistence

class FindAllClientsUseCaseImpl(
    private val clientPersistence: IClientPersistence
) : IFindAllClientsUseCase {
    override fun findAll(): List<Client> {
        return clientPersistence.findAll()
    }
}