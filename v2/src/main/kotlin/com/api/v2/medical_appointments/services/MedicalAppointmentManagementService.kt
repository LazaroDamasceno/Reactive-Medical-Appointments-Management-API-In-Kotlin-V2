package com.api.v2.medical_appointments.services

import org.springframework.http.ResponseEntity

interface MedicalAppointmentManagementService {
    suspend fun cancel(customerId: String, medicalAppointmentId: String): ResponseEntity<Unit>
}