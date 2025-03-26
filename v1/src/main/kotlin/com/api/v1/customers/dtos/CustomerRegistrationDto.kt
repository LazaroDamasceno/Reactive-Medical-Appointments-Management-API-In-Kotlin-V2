package com.api.v1.customers.dtos

import com.api.v1.common.Address
import com.api.v1.people.dtos.PersonResponseDto
import jakarta.validation.Valid

data class CustomerRegistrationDto(
    @Valid
    val personResponseDto: PersonResponseDto,
    @Valid
    val address: Address
)
