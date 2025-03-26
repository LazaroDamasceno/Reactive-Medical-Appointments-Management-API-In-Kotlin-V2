package com.api.v1.doctors.dtos

import com.api.v1.people.dtos.PersonRegistrationDto
import jakarta.validation.Valid

data class DoctorRegistrationDto(
    val medicalLicenseNumber: String,
    @Valid
    val personRegistrationDto: PersonRegistrationDto
)
