package com.api.v2.people.domain.exposed

import com.api.v2.people.dtos.PersonRegistrationDto
import com.api.v2.people.enums.Gender
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Document
class Person(
    var firstName: String,
    var middleName: String?,
    var lastName: String,
    var birthDate: LocalDate,
    val ssn: String,
    var email: String,
    var gender: Gender,
    var phoneNumber: String
) {

    @BsonId
    val id: String = UUID.randomUUID().toString()
    val createdAt: LocalDateTime = LocalDateTime.now()

    companion object {
        fun of(registrationDto: PersonRegistrationDto): Person {
            return Person(
                registrationDto.firstName,
                registrationDto.middleName,
                registrationDto.lastName,
                registrationDto.birthDate,
                registrationDto.ssn,
                registrationDto.email,
                registrationDto.gender,
                registrationDto.phoneNumber
            )
        }
    }
}