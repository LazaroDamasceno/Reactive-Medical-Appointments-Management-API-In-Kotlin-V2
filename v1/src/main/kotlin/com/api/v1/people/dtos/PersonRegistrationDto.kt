package com.api.v1.people.dtos

import com.api.v1.people.enums.Gender
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class PersonRegistrationDto(
    @NotBlank
    val firstName: String,
    val middleName: String?,
    @NotBlank
    val lastName: String,
    @NotNull
    val birthDate: LocalDate,
    @NotBlank
    @Size(min = 9, max = 9)
    val ssn: String,
    @NotBlank
    @Email
    val email: String,
    @NotNull
    val gender: Gender,
    @NotBlank
    @Size(min = 10, max = 10)
    val phoneNumber: String
)
