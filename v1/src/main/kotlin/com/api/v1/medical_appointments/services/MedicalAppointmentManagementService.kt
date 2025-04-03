package com.api.v1.medical_appointments.services

import org.springframework.http.ResponseEntity

interface MedicalAppointmentManagementService {
    suspend fun cancel(customerId: String, medicalAppointmentId: String): ResponseEntity<Unit>
}