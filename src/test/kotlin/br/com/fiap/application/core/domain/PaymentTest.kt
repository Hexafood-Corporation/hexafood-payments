package br.com.fiap.application.core.domain

import br.com.fiap.application.core.enums.PaymentStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class PaymentTest {

    @Test
    fun `should update to approved status`() {
        val payment = Payment(order = "123")
        payment.updateToApprovedStatus()

        assertEquals(PaymentStatus.APPROVED, payment.status)
        assertNotNull(payment.statusUpdatedAt)
    }

    @Test
    fun `should update to refused status`() {
        val payment = Payment(order = "123")
        payment.updateToRefusedStatus()

        assertEquals(PaymentStatus.REFUSED, payment.status)
        assertNotNull(payment.statusUpdatedAt)
    }

    @Test
    fun `should have valid equals and hashCode methods`() {
        val payment1 = Payment(id = 1, order = "1")
        val payment2 = Payment(id = 1, order = "1")
        val payment3 = Payment(id = 2, order = "2")

        assertEquals(payment1, payment2)
        assertEquals(payment1.hashCode(), payment2.hashCode())

        assertNotEquals(payment1, payment3)
        assertNotEquals(payment1.hashCode(), payment3.hashCode())
    }
}
