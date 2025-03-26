package com.api.v1.customers.services

import com.api.v1.customers.dtos.CustomerRegistrationDto

interface CustomerRegistrationService {
    suspend fun register(registrationDto: CustomerRegistrationDto)
}