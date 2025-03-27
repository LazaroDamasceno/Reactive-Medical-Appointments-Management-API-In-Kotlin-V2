package com.api.v1.medical_slots.services

import com.api.v1.medical_slots.dtos.MedicalSlotResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity

interface MedicalSlotRetrievalService {
    suspend fun findAll(): ResponseEntity<Flow<MedicalSlotResponseDto>>
    suspend fun findById(medicalSlotId: String): ResponseEntity<MedicalSlotResponseDto>
    suspend fun findById(licenseNumber: String, state: String, medicalSlotId: String): ResponseEntity<MedicalSlotResponseDto>
}