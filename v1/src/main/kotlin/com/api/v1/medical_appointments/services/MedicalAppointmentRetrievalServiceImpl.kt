package com.api.v1.medical_appointments.services

import com.api.v1.customers.utils.CustomerFinder
import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v1.medical_appointments.exceptions.InaccessibleMedicalAppointmentException
import com.api.v1.medical_appointments.utils.MedicalAppointmentFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MedicalAppointmentRetrievalServiceImpl(
    private val medicalAppointmentRepository: MedicalAppointmentRepository,
    private val customerFinder: CustomerFinder,
    private val medicalAppointmentFinder: MedicalAppointmentFinder
): MedicalAppointmentRetrievalService {

    override suspend fun findAll(): ResponseEntity<Flow<MedicalAppointmentResponseDto>> {
        return withContext(Dispatchers.IO) {
            val all = medicalAppointmentRepository
                .findAll()
                .map { ma -> ma.toDto() }
            ResponseEntity.ok(all)
        }

    }

    override suspend fun findById(
        customerId: String,
        medicalSlotId: String
    ): ResponseEntity<MedicalAppointmentResponseDto> {
        return withContext(Dispatchers.IO) {
            val customer = customerFinder.findById(customerId)
            val medicalAppointment = medicalAppointmentFinder.findById(medicalSlotId)
            if (medicalAppointment.customer.id == customer.id) {
                throw InaccessibleMedicalAppointmentException(customerId)
            }
            val dto = medicalAppointment.toDto()
            ResponseEntity.ok(dto)
        }
    }

    override suspend fun findById(medicalSlotId: String): ResponseEntity<MedicalAppointmentResponseDto> {
        return withContext(Dispatchers.IO) {
            val medicalAppointment = medicalAppointmentFinder.findById(medicalSlotId)
            val dto = medicalAppointment.toDto()
            ResponseEntity.ok(dto)
        }
    }
}