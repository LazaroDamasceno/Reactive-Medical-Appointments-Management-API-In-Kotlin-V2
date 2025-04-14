package com.api.v2.customers.services

import com.api.v2.customers.domain.exposed.Customer
import com.api.v2.customers.domain.CustomerRepository
import com.api.v2.customers.dtos.CustomerRegistrationDto
import com.api.v2.customers.dtos.exposed.CustomerResponseDto
import com.api.v2.people.exceptions.DuplicatedEmailException
import com.api.v2.people.exceptions.DuplicatedSsnException
import com.api.v2.people.services.exposed.PersonRegistrationService
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CustomerRegistrationServiceImpl(
    private val customerRepository: CustomerRepository,
    private val personRegistrationService: PersonRegistrationService
) : CustomerRegistrationService {

    override suspend fun register(registrationDto: @Valid CustomerRegistrationDto): ResponseEntity<CustomerResponseDto> {
        return withContext(Dispatchers.IO) {
            validate(registrationDto.personRegistrationDto.ssn, registrationDto.personRegistrationDto.email)
            val savedPerson = personRegistrationService.register(registrationDto.personRegistrationDto)
            val newCustomer = Customer.of(savedPerson, registrationDto.address)
            val savedCustomer = customerRepository.save(newCustomer)
            val dto = savedCustomer.toDto()
            ResponseEntity.status(HttpStatus.CREATED).body(dto)
        }
    }

    private suspend fun validate(ssn: String, email: String) {
        val isGivenSsnDuplicated = customerRepository
            .findAll()
            .firstOrNull { c -> c.person.ssn == ssn } != null
        if (isGivenSsnDuplicated) {
            throw DuplicatedSsnException()
        }

        val isGivenEmailDuplicated = customerRepository
            .findAll()
            .firstOrNull { c -> c.person.email == email } != null
        if (isGivenEmailDuplicated) {
            throw DuplicatedEmailException()
        }
    }
}