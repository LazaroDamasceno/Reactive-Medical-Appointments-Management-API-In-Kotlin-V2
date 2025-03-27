package com.api.v1.doctors.dtos.exposed

data class DoctorResponseDto(
    val fullName: String,
    val medicalLicenseNumber: MedicalLicenseNumber,
)
