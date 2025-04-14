package com.api.v2.doctors.services

import org.springframework.http.ResponseEntity

interface DoctorManagementService {
    suspend fun terminate(licenseNumber: String, state: String): ResponseEntity<Unit>
    suspend fun rehire(licenseNumber: String, state: String): ResponseEntity<Unit>
}