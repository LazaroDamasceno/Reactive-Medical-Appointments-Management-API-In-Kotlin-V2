package com.api.v1.customers.utils

import com.api.v1.customers.domain.exposed.Customer
import com.api.v1.customers.dtos.exposed.CustomerResponseDto
import com.api.v1.people.utils.formatFullName

fun Customer.toDto(): CustomerResponseDto {
    return CustomerResponseDto(
        id,
        person.formatFullName()
    )
}