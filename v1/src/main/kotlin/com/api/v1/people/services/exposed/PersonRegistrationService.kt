package com.api.v1.people.services.exposed

import com.api.v1.people.domain.exposed.Person
import com.api.v1.people.dtos.PersonRegistrationDto

interface PersonRegistrationService {
    suspend fun register(registrationDto: PersonRegistrationDto): Person
}