package com.api.v1.doctors.services

import com.api.v1.doctors.dtos.DoctorRegistrationDto
import com.api.v1.doctors.dtos.DoctorResponseDto
import org.springframework.http.ResponseEntity

interface DoctorRegistrationService {
    suspend fun register(registrationDto: DoctorRegistrationDto): ResponseEntity<DoctorResponseDto>
}