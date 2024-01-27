package br.com.fiap

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration

@SpringBootApplication
class TechChallengeApplication

fun main(args: Array<String>) {
    runApplication<TechChallengeApplication>(*args)
}