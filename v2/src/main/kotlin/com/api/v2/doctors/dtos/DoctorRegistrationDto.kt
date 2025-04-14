package com.api.v2.doctors.dtos

import com.api.v2.doctors.dtos.exposed.MedicalLicenseNumber
import com.api.v2.people.dtos.PersonRegistrationDto
import jakarta.validation.Valid

data class DoctorRegistrationDto(
    @Valid
    val medicalLicenseNumber: MedicalLicenseNumber,
    @Valid
    val personRegistrationDto: PersonRegistrationDto
)
