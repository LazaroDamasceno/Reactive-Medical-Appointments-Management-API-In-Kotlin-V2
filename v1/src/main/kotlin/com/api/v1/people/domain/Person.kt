package com.api.v1.people.domain

import com.api.v1.people.dtos.PersonRegistrationDto
import com.api.v1.people.enums.Gender
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Document
class Person {

    @BsonId
    var id: String = UUID.randomUUID().toString()
    lateinit var firstName: String
    var middleName: String? = ""
    lateinit var lastName: String
    lateinit var birthDate: LocalDate
    lateinit var ssn: String
    lateinit var gender: Gender
    lateinit var phoneNumber: String
    var createdAt: LocalDateTime = LocalDateTime.now()

    constructor()

    private constructor(registrationDto: PersonRegistrationDto) {
        this.firstName = registrationDto.firstName
        this.middleName = registrationDto.middleName
        this.lastName = registrationDto.lastName
        this.birthDate = registrationDto.birthDate
        this.ssn = registrationDto.ssn
        this.gender = registrationDto.gender
        this.phoneNumber = registrationDto.phoneNumber
    }

    companion object {
        fun of(registrationDto: PersonRegistrationDto): Person {
            return Person(registrationDto)
        }
    }
}