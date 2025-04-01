package com.api.v1.medical_slots.services

import com.api.v1.customers.utils.CustomerFinder
import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v1.medical_appointments.services.MedicalAppointmentRetrievalService
import com.api.v1.medical_slots.utils.MedicalSlotFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MedicalAppointmentRetrievalServiceImpl(
    private val customerFinder: CustomerFinder,
    private val medicalSlotFinder: MedicalSlotFinder,
    private val medicalAppointmentRepository: MedicalAppointmentRepository
): MedicalAppointmentRetrievalService {

    override suspend fun findAll(): ResponseEntity<Flow<MedicalAppointmentResponseDto>> {
        return withContext(Dispatchers.IO) {
            val all = medicalAppointmentRepository
                .findAll()
                .map { ma -> ma.toDto() }
            ResponseEntity.ok(all)
        }
    }

    override suspend fun findById(customerId: String, medicalSlotId: String): ResponseEntity<MedicalAppointmentResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(medicalSlotId: String): ResponseEntity<MedicalAppointmentResponseDto> {
        TODO("Not yet implemented")
    }
}