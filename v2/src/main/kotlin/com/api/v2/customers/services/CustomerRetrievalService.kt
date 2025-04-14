package com.api.v2.customers.services

import com.api.v2.customers.dtos.exposed.CustomerResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity

interface CustomerRetrievalService {
    suspend fun findAll(): ResponseEntity<Flow<CustomerResponseDto>>
    suspend fun findById(id: String): ResponseEntity<CustomerResponseDto>
}