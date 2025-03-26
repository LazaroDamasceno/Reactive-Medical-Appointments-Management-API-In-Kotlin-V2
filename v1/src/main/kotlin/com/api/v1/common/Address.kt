package com.api.v1.common

import jakarta.validation.constraints.NotBlank

data class Address(
    @NotBlank
    val state: String,
    @NotBlank
    val city: String,
    @NotBlank
    val street: String,
    @NotBlank
    val zipcode: String
)
