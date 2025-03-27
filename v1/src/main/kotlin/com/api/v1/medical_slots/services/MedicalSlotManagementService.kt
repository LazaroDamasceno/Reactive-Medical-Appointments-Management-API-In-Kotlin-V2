package com.api.v1.medical_slots.services

interface MedicalSlotManagementService {
    suspend fun cancel(licenseNumber: String, state: String, medicalSlotId: String)
    suspend fun completed(licenseNumber: String, state: String, medicalSlotId: String)
}