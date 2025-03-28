package com.api.v1.medical_appointments.services

interface MedicalAppointmentManagementService {
    suspend fun cancel(customerId: String, medicalAppointmentId: String)
    suspend fun complete(customerId: String, medicalAppointmentId: String)
}