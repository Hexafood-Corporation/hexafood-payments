package br.com.fiap.techchallenge.application

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class BaseDatabaseIntegrationTest