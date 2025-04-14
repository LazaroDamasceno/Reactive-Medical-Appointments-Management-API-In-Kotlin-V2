package com.api.v2.doctors.dtos.exposed

data class DoctorResponseDto(
    val fullName: String,
    val medicalLicenseNumber: MedicalLicenseNumber,
)
