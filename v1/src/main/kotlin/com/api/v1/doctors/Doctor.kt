package com.api.v1.doctors

import com.api.v1.people.domain.exposed.Person
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
class Doctor {

    @BsonId
    var id: String = UUID.randomUUID().toString()
    lateinit var person: Person
    lateinit var medicalLicenseNumber: String
    var createdAt: LocalDateTime = LocalDateTime.now()
    var terminatedAt: LocalDateTime? = null

    constructor()
}