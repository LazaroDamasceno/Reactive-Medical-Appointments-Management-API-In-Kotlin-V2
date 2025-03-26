package com.api.v1.doctors.services

import com.api.v1.doctors.dtos.DoctorRegistrationDto

interface DoctorRegistrationService {
    suspend fun register(registrationDto: DoctorRegistrationDto)
}