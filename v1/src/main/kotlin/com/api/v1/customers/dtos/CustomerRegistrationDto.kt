package com.api.v1.customers.dtos

import com.api.v1.common.Address
import com.api.v1.people.dtos.PersonRegistrationDto
import jakarta.validation.Valid

data class CustomerRegistrationDto(
    @Valid
    val personRegistrationDto: PersonRegistrationDto,
    @Valid
    val address: Address
)
