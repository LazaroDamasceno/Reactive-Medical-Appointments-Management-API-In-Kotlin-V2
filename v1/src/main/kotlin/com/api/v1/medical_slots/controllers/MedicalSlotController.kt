package com.api.v1.medical_slots.controllers

import com.api.v1.medical_slots.dtos.MedicalSlotResponseDto
import com.api.v1.medical_slots.services.MedicalSlotManagementService
import com.api.v1.medical_slots.services.MedicalSlotRegistrationService
import com.api.v1.medical_slots.services.MedicalSlotRetrievalService
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping
class MedicalSlotController(
    private val registrationService: MedicalSlotRegistrationService,
    private val managementService: MedicalSlotManagementService,
    private val retrievalService: MedicalSlotRetrievalService
) {

    @PostMapping("{licenseNumber}/{state}/{availableAt}")
    suspend fun register(
        @PathVariable licenseNumber: String,
        @PathVariable state: String,
        @PathVariable availableAt: @NotNull LocalDateTime
    ): ResponseEntity<MedicalSlotResponseDto> {
        return registrationService.register(licenseNumber, state, availableAt)
    }

    @PatchMapping("{licenseNumber}/{state}/{medicalSlotId}/cancellation")
    suspend fun cancel(@PathVariable licenseNumber: String,
                       @PathVariable state: String,
                       @PathVariable medicalSlotId: String
    ): ResponseEntity<Unit> {
        return managementService.cancel(licenseNumber, state, medicalSlotId)
    }

    @PatchMapping("{licenseNumber}/{state}/{medicalSlotId}/completion")
    suspend fun completed(@PathVariable licenseNumber: String,
                          @PathVariable state: String,
                          @PathVariable medicalSlotId: String
    ): ResponseEntity<Unit> {
        return managementService.completed(licenseNumber, state, medicalSlotId)
    }

    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<MedicalSlotResponseDto>> {
        return retrievalService.findAll()
    }

    @GetMapping("{medicalSlotId}")
    suspend fun findById(@PathVariable medicalSlotId: String): ResponseEntity<MedicalSlotResponseDto> {
        return retrievalService.findById(medicalSlotId)
    }

    @GetMapping("{licenseNumber}/{state}/{medicalSlotId}")
    suspend fun findById(
        @PathVariable licenseNumber: String,
        @PathVariable state: String,
        @PathVariable medicalSlotId: String
    ): ResponseEntity<MedicalSlotResponseDto> {
        return retrievalService.findById(licenseNumber, state, medicalSlotId)
    }

}