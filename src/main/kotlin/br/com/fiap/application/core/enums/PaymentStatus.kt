package br.com.fiap.application.core.enums

enum class PaymentStatus(prBrStatus: String) {
    APPROVED("APROVADO"),
    REFUSED("RECUSADO"),
    IN_PROCESS("INICIADO")
}