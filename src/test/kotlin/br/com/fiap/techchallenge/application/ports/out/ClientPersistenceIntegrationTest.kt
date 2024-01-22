package br.com.fiap.techchallenge.application.ports.out

import br.com.fiap.techchallenge.application.BaseDatabaseIntegrationTest
import br.com.fiap.techchallenge.application.core.domain.Client
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ClientPersistenceIntegrationTest : BaseDatabaseIntegrationTest() {

    @Autowired
    lateinit var clientPersistence: IClientPersistence

    @Test
    fun `should save a client`() {
        val clientToSave = Client(code = "1", name = "Luiz", cpf = "123123123", email = "luiz@email.com", phone = "21 9999999")
        val savedClient = clientPersistence.save(clientToSave)

        //Teste modificado pois o code é gerado automaticamente, e estava gerando um erro quando comparado com o code = 1
        assertEquals(clientToSave.cpf, savedClient.cpf)
    }

}