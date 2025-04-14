package com.api.v2.common

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class Address(
    @NotBlank
    val state: States,
    @NotBlank
    val city: String,
    @NotBlank
    val street: String,
    @NotBlank
    @Size(min = 5, max = 5)
    val zipcode: String
)
