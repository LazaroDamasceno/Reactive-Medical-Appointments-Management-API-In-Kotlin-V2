package com.api.v1.doctors.dtos

data class DoctorResponseDto(
    val fullName: String,
    val medicalLicenseNumber: MedicalLicenseNumber,
)
