package com.api.v1.medical_appointments.controllers

import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v1.medical_appointments.services.MedicalAppointmentManagementService
import com.api.v1.medical_appointments.services.MedicalAppointmentRegistrationService
import com.api.v1.medical_appointments.services.MedicalAppointmentRetrievalService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("api/v1/medical-appointments")
class MedicalAppointmentController(
    private val registrationService: MedicalAppointmentRegistrationService,
    private val managementService: MedicalAppointmentManagementService,
    private val retrievalService: MedicalAppointmentRetrievalService
) {

    @Operation(description = "Book a new medical appointment")
    @PostMapping("{licenseNumber}/{state}/{customerId}/{bookedAt}")
    suspend fun register(
        @PathVariable licenseNumber: String,
        @PathVariable state: String,
        @PathVariable customerId: String,
        @PathVariable bookedAt: @NotNull LocalDateTime
    ): ResponseEntity<MedicalAppointmentResponseDto> {
        return registrationService.register(licenseNumber, state, customerId, bookedAt)
    }

    @Operation(description = "Cancel a medical appointment")
    @PatchMapping("{customerId}/{medicalAppointmentId}/cancellation")
    suspend fun cancel(@PathVariable customerId: String,
                       @PathVariable medicalAppointmentId: String
    ): ResponseEntity<Unit> {
        return managementService.cancel(customerId, medicalAppointmentId)
    }


    @Operation(description = "Complete a medical appointment")
    suspend fun complete(@PathVariable customerId: String,
                         @PathVariable medicalAppointmentId: String
    ): ResponseEntity<Unit> {
        return managementService.complete(customerId, medicalAppointmentId)
    }

    @Operation(description = "Retrieval all medical appointments")
    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<MedicalAppointmentResponseDto>> {
        return retrievalService.findAll()
    }

    @Operation(description = "Retrieval a medical appointment by customer and its id")
    @GetMapping("{customerId}/{medicalSlotId}")
    suspend fun findById(
        customerId: String,
        medicalSlotId: String
    ): ResponseEntity<MedicalAppointmentResponseDto> {
        return retrievalService.findById(customerId, medicalSlotId)
    }

    @Operation(description = "Retrieval a medical appointment by its id")
    @GetMapping("{medicalSlotId}")
    suspend fun findById(medicalSlotId: String): ResponseEntity<MedicalAppointmentResponseDto> {
        return retrievalService.findById(medicalSlotId)
    }
}