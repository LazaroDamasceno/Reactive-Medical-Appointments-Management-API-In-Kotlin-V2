package com.api.v1.customers.utils

import com.api.v1.customers.domain.Customer
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.people.utils.formatFullName

fun Customer.toDto(): CustomerResponseDto {
    return CustomerResponseDto(
        id,
        person.formatFullName()
    )
}