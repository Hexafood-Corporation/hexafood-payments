package br.com.fiap.techchallenge.application.ports.`in`

interface IActivationProductUseCase {
    fun enabled(sku: String)
    fun disabled(sku: String)
}