package com.api.v1.medical_slots.services

import com.api.v1.medical_slots.dtos.MedicalSlotResponseDto
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

interface MedicalSlotRegistrationService {
    suspend fun register(licenseNumber: String, state: String, availableAt: LocalDateTime): ResponseEntity<MedicalSlotResponseDto>
}