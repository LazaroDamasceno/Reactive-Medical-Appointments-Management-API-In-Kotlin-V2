package com.api.v1.doctors.utils

import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.people.utils.formatFullName

fun Doctor.toDto(): DoctorResponseDto {
    return DoctorResponseDto(
        person.formatFullName(),
        medicalLicenseNumber
    )
}