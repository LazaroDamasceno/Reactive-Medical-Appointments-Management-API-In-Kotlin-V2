package com.api.v1.medical_slots.services

import org.springframework.http.ResponseEntity

interface MedicalSlotManagementService {
    suspend fun cancel(licenseNumber: String, state: String, medicalSlotId: String): ResponseEntity<Unit>
    suspend fun completed(licenseNumber: String, state: String, medicalSlotId: String, price: Double): ResponseEntity<Unit>
}