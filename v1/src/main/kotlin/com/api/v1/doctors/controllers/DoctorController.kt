package com.api.v1.doctors.controllers

import com.api.v1.doctors.dtos.DoctorRegistrationDto
import com.api.v1.doctors.dtos.exposed.DoctorResponseDto
import com.api.v1.doctors.services.DoctorManagementService
import com.api.v1.doctors.services.DoctorRegistrationService
import com.api.v1.doctors.services.DoctorRetrievalService
import io.swagger.v3.oas.annotations.Operation
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

    @Operation(summary = "Register a new doctor")
    @PostMapping
    suspend fun register(@RequestBody registrationDto: @Valid DoctorRegistrationDto): ResponseEntity<DoctorResponseDto> {
        return registrationService.register(registrationDto)
    }

    @Operation(summary = "Terminate a doctor")
    @PatchMapping("{licenseNumber}/{state}/cancellation")
    suspend fun terminate(
        @PathVariable licenseNumber: String,
        @PathVariable state: String
    ): ResponseEntity<Unit> {
        return managementService.terminate(licenseNumber, state)
    }

    @Operation(summary = "Rehire a doctor")
    @PatchMapping("{licenseNumber}/{state}/rehiring")
    suspend fun rehire(
        @PathVariable licenseNumber: String,
        @PathVariable state: String
    ): ResponseEntity<Unit> {
        return managementService.rehire(licenseNumber, state)
    }

    @Operation(summary = "Retrieve a doctor")
    @GetMapping("{licenseNumber}/{state}")
    suspend fun findByMedicalLicenseNumber(
        @PathVariable licenseNumber: String,
        @PathVariable state: String
    ): ResponseEntity<DoctorResponseDto> {
        return retrievalService.findByMedicalLicenseNumber(licenseNumber, state)
    }

    @Operation(summary = "Retrieve all doctors")
    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<DoctorResponseDto>> {
        return retrievalService.findAll()
    }
}