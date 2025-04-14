package com.api.v2.customers.controllers

import com.api.v2.customers.dtos.CustomerRegistrationDto
import com.api.v2.customers.dtos.exposed.CustomerResponseDto
import com.api.v2.customers.services.CustomerRegistrationService
import com.api.v2.customers.services.CustomerRetrievalService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v2/customers")
class CustomerController(
    private val registrationService: CustomerRegistrationService,
    private val retrievalService: CustomerRetrievalService
) {

    @Operation(summary = "Register a new medical slot")
    @PostMapping
    suspend fun register(@RequestBody registrationDto: @Valid CustomerRegistrationDto): ResponseEntity<CustomerResponseDto> {
        return registrationService.register(registrationDto)
    }

    @Operation(summary = "Retrieve all medical slots")
    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<CustomerResponseDto>> {
        return retrievalService.findAll()
    }

    @Operation(summary = "Retrieve one medical slot by its id")
    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: String): ResponseEntity<CustomerResponseDto> {
        return retrievalService.findById(id)
    }


}