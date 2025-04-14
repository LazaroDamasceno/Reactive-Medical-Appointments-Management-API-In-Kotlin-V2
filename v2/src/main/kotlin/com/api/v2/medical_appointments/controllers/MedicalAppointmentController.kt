package com.api.v2.medical_appointments.controllers

import com.api.v2.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v2.medical_appointments.services.MedicalAppointmentManagementService
import com.api.v2.medical_appointments.services.MedicalAppointmentRegistrationService
import com.api.v2.medical_appointments.services.MedicalAppointmentRetrievalService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("api/v2/medical-appointments")
class MedicalAppointmentController(
    private val registrationService: MedicalAppointmentRegistrationService,
    private val managementService: MedicalAppointmentManagementService,
    private val retrievalService: MedicalAppointmentRetrievalService
) {

    @Operation(summary = "Book a new medical appointment")
    @PostMapping("{licenseNumber}/{state}/{customerId}/{bookedAt}")
    suspend fun register(
        @PathVariable licenseNumber: String,
        @PathVariable state: String,
        @PathVariable customerId: String,
        @PathVariable bookedAt: @NotNull LocalDateTime
    ): ResponseEntity<MedicalAppointmentResponseDto> {
        return registrationService.register(licenseNumber, state, customerId, bookedAt)
    }

    @Operation(summary = "Cancel a medical appointment")
    @PatchMapping("{customerId}/{medicalAppointmentId}/cancellation")
    suspend fun cancel(@PathVariable customerId: String,
                       @PathVariable medicalAppointmentId: String
    ): ResponseEntity<Unit> {
        return managementService.cancel(customerId, medicalAppointmentId)
    }

    @Operation(summary = "Retrieval all medical appointments")
    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<MedicalAppointmentResponseDto>> {
        return retrievalService.findAll()
    }

    @Operation(summary = "Retrieval a medical appointment by customer and its id")
    @GetMapping("{customerId}/{medicalSlotId}")
    suspend fun findById(
        customerId: String,
        medicalSlotId: String
    ): ResponseEntity<MedicalAppointmentResponseDto> {
        return retrievalService.findById(customerId, medicalSlotId)
    }

    @Operation(summary = "Retrieval a medical appointment by its id")
    @GetMapping("{medicalSlotId}")
    suspend fun findById(medicalSlotId: String): ResponseEntity<MedicalAppointmentResponseDto> {
        return retrievalService.findById(medicalSlotId)
    }
}