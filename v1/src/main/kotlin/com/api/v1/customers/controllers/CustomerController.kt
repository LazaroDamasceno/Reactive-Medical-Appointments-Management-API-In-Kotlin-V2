package com.api.v1.customers.controllers

import com.api.v1.customers.dtos.CustomerRegistrationDto
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.services.CustomerRegistrationService
import com.api.v1.customers.services.CustomerRetrievalService
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/customers")
class CustomerController(
    private val registrationService: CustomerRegistrationService,
    private val retrievalService: CustomerRetrievalService
) {

    @PostMapping
    suspend fun register(@RequestBody registrationDto: @Valid CustomerRegistrationDto): ResponseEntity<CustomerResponseDto> {
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