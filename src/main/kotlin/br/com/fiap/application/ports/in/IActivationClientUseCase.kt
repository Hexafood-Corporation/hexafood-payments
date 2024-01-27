package br.com.fiap.application.ports.`in`

interface IActivationClientUseCase {
    fun activate(code: String)
    fun deactivate(code: String)
}