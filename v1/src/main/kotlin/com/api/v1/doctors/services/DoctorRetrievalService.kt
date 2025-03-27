package com.api.v1.doctors.services

import com.api.v1.doctors.dtos.exposed.DoctorResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity

interface DoctorRetrievalService {
    suspend fun findByMedicalLicenseNumber(licenseNumber: String, state: String): ResponseEntity<DoctorResponseDto>
    suspend fun findAll(): ResponseEntity<Flow<DoctorResponseDto>>
}