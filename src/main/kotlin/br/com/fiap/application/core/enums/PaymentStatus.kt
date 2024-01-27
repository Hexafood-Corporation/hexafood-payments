package br.com.fiap.application.core.enums

enum class PaymentStatus(val prBrStatus: String) {
    APPROVED("APROVADO"),
    REFUSED("RECUSADO"),
    IN_PROCESS("INICIADO");

    companion object {
        fun getStatus(status: String): PaymentStatus? =
            PaymentStatus.values().find { it.prBrStatus.equals(status, ignoreCase = true) }
    }
}
