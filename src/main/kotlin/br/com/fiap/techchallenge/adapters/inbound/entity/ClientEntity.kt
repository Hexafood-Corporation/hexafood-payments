package br.com.fiap.techchallenge.adapters.inbound.entity

import br.com.fiap.techchallenge.application.core.domain.Client
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "clients")
data class ClientEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var code: String? = null,
    val name: String,
    val cpf: String,
    val email: String,
    val phone: String,
    private var active: Boolean = true,

    @CreationTimestamp
    val creationDate: OffsetDateTime? = null
) {
    @PrePersist
    fun generateCode() {
        code = UUID.randomUUID().toString()
    }

    fun toClient(): Client {
        return Client(id!!, code!!, name, cpf, email, phone, active)
    }

    fun activate() {
        active = true
    }

    fun deactivate() {
        active = false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClientEntity

        if (id != other.id) return false
        if (code != other.code) return false
        if (name != other.name) return false
        if (cpf != other.cpf) return false
        if (email != other.email) return false
        if (phone != other.phone) return false
        if (active != other.active) return false
        return creationDate == other.creationDate
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (code?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + cpf.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + active.hashCode()
        result = 31 * result + (creationDate?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ClientEntity(id=$id, code=$code, name='$name', cpf='$cpf', email='$email', phone='$phone', active=$active, creationDate=$creationDate)"
    }

}

fun Client.toEntity(): ClientEntity {
    return ClientEntity(
        id = id,
        code = code,
        name = name,
        cpf = cpf,
        email = email,
        phone = phone,
        active = active!!)
}


