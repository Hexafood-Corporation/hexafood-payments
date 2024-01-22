package br.com.fiap.techchallenge.adapters.outbound.service

import br.com.fiap.techchallenge.adapters.inbound.entity.toEntity
import br.com.fiap.techchallenge.adapters.outbound.repository.IClientRepository
import br.com.fiap.techchallenge.application.core.domain.Client
import br.com.fiap.techchallenge.application.ports.out.IClientPersistence
import org.springframework.stereotype.Service

@Service
class ClientPersistenceImpl(
    private val clientRepository: IClientRepository
) : IClientPersistence {
    override fun save(client: Client): Client {
        return clientRepository.save(client.toEntity()).toClient()
    }

    override fun findByCode(code: String): Client? {
        return clientRepository.findByCode(code)?.toClient()
    }

    override fun findAll(): List<Client> {
        return clientRepository.findAll().map { it.toClient() }
    }

    override fun activate(code: String) {
        val client = clientRepository.findByCode(code)
        client?.let {
            it.activate()
            clientRepository.save(it)
        }
    }

    override fun deactivate(code: String) {
        val client = clientRepository.findByCode(code)
        client?.let {
            it.deactivate()
            clientRepository.save(it)
        }
    }

    override fun findByCpf(cpf: String): Client? {
        return clientRepository.findByCpf(cpf)?.toClient()
    }
}