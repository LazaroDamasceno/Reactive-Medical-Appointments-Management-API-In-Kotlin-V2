package com.api.v1.customers.domain

import com.api.v1.common.Address
import com.api.v1.people.domain.exposed.Person
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
class Customer {

    @BsonId
    var id: String = UUID.randomUUID().toString()
    lateinit var person: Person
    lateinit var address: Address
    var createdAt: LocalDateTime = LocalDateTime.now()

    constructor()

    private constructor(person: Person, address: Address) {
        this.person = person
        this.address = address
    }

    companion object {
        fun of(person: Person, address: Address): Customer {
            return Customer(person, address)
        }
    }
}