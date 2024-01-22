package br.com.fiap.techchallenge.adapters.outbound.repository

import br.com.fiap.techchallenge.adapters.inbound.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IProductRepository : JpaRepository<ProductEntity, Int> {
    fun findByProductType(productType: String): List<ProductEntity>
    fun findBySku(sku: String): ProductEntity?

}