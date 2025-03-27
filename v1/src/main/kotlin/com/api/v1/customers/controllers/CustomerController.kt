package com.api.v1.customers.controllers

import com.api.v1.customers.dtos.CustomerRegistrationDto
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.services.CustomerRegistrationService
import com.api.v1.customers.services.CustomerRetrievalService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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