package com.api.v1.medical_appointments.services

import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity

interface MedicalAppointmentRetrievalService {
    suspend fun findAll(): ResponseEntity<Flow<MedicalAppointmentResponseDto>>
    suspend fun findById(customerId: String, medicalSlotId: String): ResponseEntity<MedicalAppointmentResponseDto>
    suspend fun findById(medicalSlotId: String): ResponseEntity<MedicalAppointmentResponseDto>
}