package com.api.v2.medical_appointments.services

import com.api.v2.medical_appointments.dtos.MedicalAppointmentResponseDto
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

interface MedicalAppointmentRegistrationService {
    suspend fun register(licenseNumber: String,
                         state: String,
                         customerId: String,
                         bookedAt: LocalDateTime
    ): ResponseEntity<MedicalAppointmentResponseDto>
}