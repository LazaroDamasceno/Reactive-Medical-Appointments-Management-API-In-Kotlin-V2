package com.api.v1.customers.services

import com.api.v1.customers.dtos.CustomerResponseDto
import kotlinx.coroutines.flow.Flow

interface CustomerRetrievalService {
    suspend fun findAll(): Flow<CustomerResponseDto>
    suspend fun findById(id: String): CustomerResponseDto
}