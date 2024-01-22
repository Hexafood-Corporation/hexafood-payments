package br.com.fiap.techchallenge.application.core.enums

enum class OrderStatus(val index: Int ) {
    RECEIVED(2),
    IN_PREPARATION(1),
    READY(0),
    FINISHED(3)
}