package br.com.fiap.techchallenge.application.core.usecase

import br.com.fiap.techchallenge.application.ports.`in`.IActivationClientUseCase
import br.com.fiap.techchallenge.application.ports.out.IClientPersistence

class ActivationClientUseCaseImpl(
    private val clientPersistence: IClientPersistence
) : IActivationClientUseCase {
    override fun activate(code: String) {
        clientPersistence.activate(code)
    }

    override fun deactivate(code: String) {
        clientPersistence.deactivate(code)
    }

}