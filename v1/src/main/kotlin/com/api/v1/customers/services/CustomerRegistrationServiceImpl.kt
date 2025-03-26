package com.api.v1.customers.services

import com.api.v1.customers.domain.Customer
import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.dtos.CustomerRegistrationDto
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.utils.toDto
import com.api.v1.people.exceptions.DuplicatedSsnException
import com.api.v1.people.services.exposed.PersonRegistrationService
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
            onDuplicatedSsn(registrationDto.personRegistrationDto.ssn)
            onDuplicatedEmail(registrationDto.personRegistrationDto.email)
            val savedPerson = personRegistrationService.register(registrationDto.personRegistrationDto)
            val newCustomer = Customer.of(savedPerson, registrationDto.address)
            val savedCustomer = customerRepository.save(newCustomer)
            val dto = savedCustomer.toDto()
            ResponseEntity.status(HttpStatus.CREATED).body(dto)
        }
    }

    private suspend fun onDuplicatedSsn(ssn: String) {
        val isGivenSsnDuplicated = customerRepository
            .findAll()
            .firstOrNull { c -> c.person.ssn == ssn } != null
        if (isGivenSsnDuplicated) {
            throw DuplicatedSsnException()
        }
    }

    private suspend fun onDuplicatedEmail(email: String) {
        val isGivenSsnDuplicated = customerRepository
            .findAll()
            .firstOrNull { c -> c.person.email == email } != null
        if (isGivenSsnDuplicated) {
            throw DuplicatedSsnException()
        }
    }
}