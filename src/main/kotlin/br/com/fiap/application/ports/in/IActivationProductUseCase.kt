package br.com.fiap.application.ports.`in`

interface IActivationProductUseCase {
    fun enabled(sku: String)
    fun disabled(sku: String)
}