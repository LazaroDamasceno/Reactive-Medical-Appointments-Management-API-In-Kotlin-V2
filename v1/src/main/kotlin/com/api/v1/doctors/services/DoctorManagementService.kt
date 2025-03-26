package com.api.v1.doctors.services

interface DoctorManagementService {
    suspend fun terminate(licenseNumber: String, state: String)
    suspend fun rehire(licenseNumber: String, state: String)
}