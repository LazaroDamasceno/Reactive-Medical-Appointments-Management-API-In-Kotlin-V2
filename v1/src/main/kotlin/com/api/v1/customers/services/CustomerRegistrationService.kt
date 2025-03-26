package com.api.v1.customers.services

import com.api.v1.customers.dtos.CustomerRegistrationDto
import com.api.v1.customers.dtos.CustomerResponseDto
import org.springframework.http.ResponseEntity

interface CustomerRegistrationService {
    suspend fun register(registrationDto: CustomerRegistrationDto): ResponseEntity<CustomerResponseDto>
}