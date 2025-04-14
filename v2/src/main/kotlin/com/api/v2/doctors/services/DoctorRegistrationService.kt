package com.api.v2.doctors.services

import com.api.v2.doctors.dtos.DoctorRegistrationDto
import com.api.v2.doctors.dtos.exposed.DoctorResponseDto
import org.springframework.http.ResponseEntity

interface DoctorRegistrationService {
    suspend fun register(registrationDto: DoctorRegistrationDto): ResponseEntity<DoctorResponseDto>
}