<h1 align="center">
    Hexafood - Payments
</h1>

## :boat: Sobre o projeto

Esse projeto faz parte do trabalho "Tech Challenge - Fase 04", ministrado no quarto módulo do curso de Pós Graduação Software Architecture da FIAP em parceria com a Alura.

É um microserviço de pagamento, onde sua comunicaçao é através de mensageria.

## :hammer: Tecnologias:

- **[SpringBoot](https://spring.io/projects/spring-boot)**
- **[Kotlin](https://kotlinlang.org/)**
- **[Postgresql](https://www.postgresql.org/)**
- **[Sqs](https://aws.amazon.com/pt/sqs/)**

## :rocket: Como rodar esse projeto

Se você estiver usando Windows, vai precisar do WSL para rodar esse projeto de forma prática. Para isso, você pode instalá-lo seguindo o seguinte [tutorial](https://learn.microsoft.com/pt-br/windows/wsl/install). Também será necessário uma distribuição linux para utilizar o WSL. Recomendo o Ubuntu que pode ser baixando na própria Microsoft Store no [link](https://apps.microsoft.com/store/detail/ubuntu/9PDXGNCFSCZV).
Depois, vai precisar do Docker, o qual a versão de Windows pode ser encontrada [aqui](https://docs.docker.com/desktop/install/windows-install/).
Então, clone o projeto dentro do WSL, vá para pasta dele e execute o comando:

```
docker compose build --no-cache
```

Após a construção da imagem, basta executar o comando:

```
docker compose up
```

O projeto estará executando no endereço http://localhost:8080/.

Para limpar o volume db do docker, execute o comando:

```
docker-compose down -v
```
### Filas

A aplicaçao hexafood-payments fica escutando a fila de 'novo_pedido' com o seguinte contrato:

```json
{
  "id": 2,
  "codigo_pedido": "X5UO5W",
  "valor_total": 10,
  "status": "INICIADO",
  "itens": [
    {
      "id": 1,
      "quantidade": 1,
      "valor": 10,
      "produto": {
        "id": 1,
        "nome": "Hamburger"
      }
    }
  ],
  "cliente": {
    "id": 1,
    "nome": "Teste",
    "cpf": "12345678910"
  }
}
```

Listener:
```kotlin
@Service
class PaymentConsumer(
    private val createPaymentUseCase: ICreatePaymentUseCase,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @SqsListener("novo_pedido")
    fun onMessage(order: CreatePaymentRequest) {
        try {
            logger.info("**** Receiving message from queue novo_pedido with order_id=${order.codigoPedido}****")

            createPaymentUseCase.save(order.toPayment()).runCatching {  }

            logger.info("**** message from novo_pedido with order_id=${order.codigoPedido} was saved in database ****")
        } catch (e: Exception) {
            logger.info("**** message wasn't save, because the order_id=${order.codigoPedido} already exist! ****")
        }
    }
}
```

O Pedido é processado e salvo na tabela PAYMENTS com o status 'IN_PROGRESS', a aplicacao nao aceita 'codigo_pedido' duplicado para processamento. 

Em seguida, temos um schedule para pegar os registros com status 'IN_PROGRESS' e fazer a manipulacao:
```kotlin
@Service
@EnableScheduling
class PaymentProducer(
    private val queueMessagingTemplate: QueueMessagingTemplate,
    private val repository: IFindPaymentsByStatusUseCase,
    private val paymentStatus: IPaymentStatusManagerUseCase
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val queueFinishedPayment = "pagamento_processado"

    @Scheduled(fixedDelay = 1000)
    fun SendMessage() {
        val paymentesInProcess = repository.findByStatus(PaymentStatus.IN_PROCESS)

        paymentesInProcess?.forEach {
            logger.info("**** Processing Payment ****")
            paymentStatus.updateToApprovedStatus(it.paymentId!!)
            it.status = PaymentStatus.APPROVED

            logger.info("**** Publishing payment APPROVED with order_id= ${it.order} to queue ****")

            sendMessageToSqs(queueFinishedPayment, it.toPaymentResponse())
        }

    }
    fun sendMessageToSqs(queueName: String, message: PaymentResponse) {

        logger.info("**** Published message to pagamento_processado queue ****")

        queueMessagingTemplate.convertAndSend<Any>(queueName, message) //send to queue
    }
}
```

a mensagem publicada na fila 'pagamento_processado':

```json
{
  "id_pagamento": "4ecddff8-7aee-43ac-ba58-e32d5d9d5030",
  "id_pedido": 1,
  "status": "APROVADO",
  "update_at" : "",
  "metodo_pagamento": "PIX"
}

```

🔌 Cobertura de testes com SonarCloud
A fim de atender aos critérios de Qualidade de Software do desafio, foi implementao testes unitários neste microsserviço, e configurado a pipeline para executar uma verificação a cada push na branch main. Dessa forma, caso o código está com menos de 80% de cobertura de testes, ele é rejeitado. Na imagem seguir, temos um exemplo de report anexado a PR informando a análise do SonarCloud:

<img width="500" alt="Captura de Tela 2024-02-16 às 15 27 05" src="https://github.com/Hexafood-Corporation/hexafood-payments/assets/15147926/49475609-85e0-421d-8dca-6c6bc25ba7ba">




