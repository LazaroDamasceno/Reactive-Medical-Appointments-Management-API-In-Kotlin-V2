package com.api.v1.customers.controllers

import com.api.v1.customers.dtos.CustomerRegistrationDto
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.services.CustomerRegistrationService
import com.api.v1.customers.services.CustomerRetrievalService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/customers")
class CustomerController {

    private final var registrationService: CustomerRegistrationService
    private final var retrievalService: CustomerRetrievalService

    constructor(registrationService: CustomerRegistrationService,
                retrievalService: CustomerRetrievalService
    ) {
        this.registrationService = registrationService
        this.retrievalService = retrievalService
    }

    @PostMapping
    suspend fun register(registrationDto: CustomerRegistrationDto): ResponseEntity<CustomerResponseDto> {
        return registrationService.register(registrationDto)
    }

    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<CustomerResponseDto>> {
        return retrievalService.findAll()
    }

    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: String): ResponseEntity<CustomerResponseDto> {
        return retrievalService.findById(id)
    }


}