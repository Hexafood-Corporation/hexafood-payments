package br.com.fiap.techchallenge.application.ports.`in`

interface IActivationClientUseCase {
    fun activate(code: String)
    fun deactivate(code: String)
}