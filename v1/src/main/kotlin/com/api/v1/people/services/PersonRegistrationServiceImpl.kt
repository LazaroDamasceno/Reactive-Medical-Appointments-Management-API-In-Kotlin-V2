package com.api.v1.people.services

import com.api.v1.people.domain.exposed.Person
import com.api.v1.people.domain.PersonRepository
import com.api.v1.people.dtos.PersonRegistrationDto
import com.api.v1.people.services.exposed.PersonRegistrationService
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class PersonRegistrationServiceImpl(
    private val personRepository: PersonRepository
) : PersonRegistrationService {

    override suspend fun register(registrationDto: @Valid PersonRegistrationDto): Person {
        return withContext(Dispatchers.IO) {
            val foundPerson = personRepository
                .findAll()
                .firstOrNull { p ->
                    p.ssn == registrationDto.ssn ||
                    p.email == registrationDto.email
                }
           if (foundPerson == null) {
                val newPerson = Person.of(registrationDto)
                val savedPerson = personRepository.save(newPerson)
                return@withContext savedPerson
            }
           foundPerson
        }
    }
}