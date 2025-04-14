package com.api.v2.medical_slots.controllers

import com.api.v2.medical_slots.dtos.MedicalSlotResponseDto
import com.api.v2.medical_slots.services.MedicalSlotManagementService
import com.api.v2.medical_slots.services.MedicalSlotRegistrationService
import com.api.v2.medical_slots.services.MedicalSlotRetrievalService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("api/v2/medical-slots")
class MedicalSlotController(
    private val registrationService: MedicalSlotRegistrationService,
    private val managementService: MedicalSlotManagementService,
    private val retrievalService: MedicalSlotRetrievalService
) {

    @Operation(summary = "Register a new medical slot")
    @PostMapping("{licenseNumber}/{state}/{availableAt}")
    suspend fun register(
        @PathVariable licenseNumber: String,
        @PathVariable state: String,
        @PathVariable availableAt: @NotNull LocalDateTime
    ): ResponseEntity<MedicalSlotResponseDto> {
        return registrationService.register(licenseNumber, state, availableAt)
    }

    @Operation(summary = "Cancel a medical slot")
    @PatchMapping("{licenseNumber}/{state}/{medicalSlotId}/cancellation")
    suspend fun cancel(@PathVariable licenseNumber: String,
                       @PathVariable state: String,
                       @PathVariable medicalSlotId: String
    ): ResponseEntity<Unit> {
        return managementService.cancel(licenseNumber, state, medicalSlotId)
    }

    @Operation(summary = "Complete a medical slot")
    @PatchMapping("{licenseNumber}/{state}/{medicalSlotId}/completion")
    suspend fun completed(@PathVariable licenseNumber: String,
                          @PathVariable state: String,
                          @PathVariable medicalSlotId: String,
                          @PathVariable price: Double
    ): ResponseEntity<Unit> {
        return managementService.completed(licenseNumber, state, medicalSlotId, price)
    }

    @Operation(summary = "Retrieve all medical slots")
    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<MedicalSlotResponseDto>> {
        return retrievalService.findAll()
    }

    @Operation(summary = "Retrieve one medical slot by its id")
    @GetMapping("{medicalSlotId}")
    suspend fun findById(@PathVariable medicalSlotId: String): ResponseEntity<MedicalSlotResponseDto> {
        return retrievalService.findById(medicalSlotId)
    }

    @Operation(summary = "Retrieve one medical slot by its id and doctor")
    @GetMapping("{licenseNumber}/{state}/{medicalSlotId}")
    suspend fun findById(
        @PathVariable licenseNumber: String,
        @PathVariable state: String,
        @PathVariable medicalSlotId: String
    ): ResponseEntity<MedicalSlotResponseDto> {
        return retrievalService.findById(licenseNumber, state, medicalSlotId)
    }

}