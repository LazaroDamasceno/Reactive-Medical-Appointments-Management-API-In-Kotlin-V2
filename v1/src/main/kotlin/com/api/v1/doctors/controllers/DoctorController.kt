package com.api.v1.doctors.controllers

import com.api.v1.doctors.dtos.DoctorRegistrationDto
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.services.DoctorManagementService
import com.api.v1.doctors.services.DoctorRegistrationService
import com.api.v1.doctors.services.DoctorRetrievalService
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/doctors")
class DoctorController(
    private val registrationService: DoctorRegistrationService,
    private val managementService: DoctorManagementService,
    private val retrievalService: DoctorRetrievalService
) {

    @PostMapping
    suspend fun register(@RequestBody registrationDto: @Valid DoctorRegistrationDto): ResponseEntity<DoctorResponseDto> {
        return registrationService.register(registrationDto)
    }

    @PatchMapping("{licenseNumber}/{state}/cancellation")
    suspend fun terminate(
        @PathVariable licenseNumber: String,
        @PathVariable state: String
    ): ResponseEntity<Unit> {
        return managementService.terminate(licenseNumber, state)
    }

    @PatchMapping("{licenseNumber}/{state}/rehiring")
    suspend fun rehire(
        @PathVariable licenseNumber: String,
        @PathVariable state: String
    ): ResponseEntity<Unit> {
        return managementService.rehire(licenseNumber, state)
    }

    @GetMapping("{licenseNumber}/{state}")
    suspend fun findByMedicalLicenseNumber(
        @PathVariable licenseNumber: String,
        @PathVariable state: String
    ): ResponseEntity<DoctorResponseDto> {
        return retrievalService.findByMedicalLicenseNumber(licenseNumber, state)
    }

    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<DoctorResponseDto>> {
        return retrievalService.findAll()
    }
}