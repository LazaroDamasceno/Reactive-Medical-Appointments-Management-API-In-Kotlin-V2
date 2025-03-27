package com.api.v1.medical_slots.services

import java.time.LocalDateTime

interface MedicalSlotRegistrationService {
    suspend fun register(licenseNumber: String, state: String, availableAt: LocalDateTime)
}