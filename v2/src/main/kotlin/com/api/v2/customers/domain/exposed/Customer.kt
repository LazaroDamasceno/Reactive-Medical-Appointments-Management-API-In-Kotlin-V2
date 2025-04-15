package com.api.v2.customers.domain.exposed

import com.api.v2.common.Address
import com.api.v2.customers.dtos.exposed.CustomerResponseDto
import com.api.v2.people.domain.exposed.Person
import com.api.v2.people.utils.formatFullName
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
class Customer(
    var person: Person,
    var address: Address
) {

    @BsonId
    val id: String = UUID.randomUUID().toString()
    val createdAt: LocalDateTime = LocalDateTime.now()

    companion object {
        fun of(person: Person, address: Address): Customer {
            return Customer(person, address)
        }
    }

    fun toDto(): CustomerResponseDto {
        return CustomerResponseDto(
            id,
            person.formatFullName()
        )
    }
}