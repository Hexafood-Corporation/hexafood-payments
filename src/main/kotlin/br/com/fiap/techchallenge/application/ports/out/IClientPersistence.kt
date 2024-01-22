package br.com.fiap.techchallenge.application.ports.out

import br.com.fiap.techchallenge.application.core.domain.Client

interface IClientPersistence {
    fun save(client: Client): Client
    fun findByCode(code: String): Client?
    fun findAll(): List<Client>
    fun activate(code: String)
    fun deactivate(code: String)
    fun findByCpf(cpf: String): Client?

}