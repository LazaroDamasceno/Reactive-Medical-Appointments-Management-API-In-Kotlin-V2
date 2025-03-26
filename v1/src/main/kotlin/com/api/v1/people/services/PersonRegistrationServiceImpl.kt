package com.api.v1.people.services

import com.api.v1.people.domain.Person
import com.api.v1.people.domain.PersonRepository
import com.api.v1.people.dtos.PersonRegistrationDto
import com.api.v1.people.services.exposed.PersonRegistrationService
import jakarta.validation.Valid

class PersonRegistrationServiceImpl: PersonRegistrationService {

    private var personRepository: PersonRepository

    constructor(personRepository: PersonRepository) {
        this.personRepository = personRepository
    }

    override suspend fun register(registrationDto: @Valid PersonRegistrationDto): Person {
        val foundPerson = personRepository
            .findAll()
            .firstOrNull { p ->
                p.ssn == registrationDto.ssn ||
                p.email == registrationDto.email
            }
        if (foundPerson == null) {
            val person = Person.of(registrationDto)
            return personRepository.save(person)
        }
        return foundPerson
    }
}