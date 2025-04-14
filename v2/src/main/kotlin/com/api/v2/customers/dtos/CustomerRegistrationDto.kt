package com.api.v2.customers.dtos

import com.api.v2.common.Address
import com.api.v2.people.dtos.PersonRegistrationDto
import jakarta.validation.Valid

data class CustomerRegistrationDto(
    @Valid
    val personRegistrationDto: PersonRegistrationDto,
    @Valid
    val address: Address
)
