package com.api.v1.doctors.utils

import com.api.v1.doctors.domain.Doctor
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.people.utils.formatFullName

fun Doctor.toDto(): DoctorResponseDto {
    return DoctorResponseDto(
        medicalLicenseNumber,
        person.formatFullName()
    )
}