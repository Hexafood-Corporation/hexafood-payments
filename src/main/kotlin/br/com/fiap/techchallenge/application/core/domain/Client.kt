package br.com.fiap.techchallenge.application.core.domain

data class Client(
    var id: Int? = null,
    val code: String? = null,
    val name: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val active: Boolean? = true
) {
    fun isValid(): Boolean {
        return name.isNotBlank()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (id != other.id) return false
        if (code != other.code) return false
        if (name != other.name) return false
        if (cpf != other.cpf) return false
        if (email != other.email) return false
        if (phone != other.phone) return false
        return active == other.active
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + code.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + cpf.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + active.hashCode()
        return result
    }

    override fun toString(): String {
        return "Client(code=$code, name='$name', cpf='$cpf', email='$email', phone='$phone', active=$active)"
    }


}


