package com.api.v1.medical_slots.services

import com.api.v1.doctors.utils.DoctorFinder
import com.api.v1.medical_slots.domain.MedicalSlotRepository
import com.api.v1.medical_slots.dtos.MedicalSlotResponseDto
import com.api.v1.medical_slots.exceptions.InaccessibleMedicalSlotException
import com.api.v1.medical_slots.utils.MedicalSlotFinder
import com.api.v1.medical_slots.utils.toDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MedicalSlotRetrievalServiceImpl(
    private val medicalSlotFinder: MedicalSlotFinder,
    private val doctorFinder: DoctorFinder,
    private val medicalSlotRepository: MedicalSlotRepository
): MedicalSlotRetrievalService {

    override suspend fun findAll(): ResponseEntity<Flow<MedicalSlotResponseDto>> {
        return withContext(Dispatchers.IO) {
            val all = medicalSlotRepository
                .findAll()
                .map { ms -> ms.toDto() }
            ResponseEntity.ok(all)
        }
    }

    override suspend fun findAll(
        licenseNumber: String,
        state: String
    ): ResponseEntity<Flow<MedicalSlotResponseDto>> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state)
            val all = medicalSlotRepository
                .findAll()
                .filter { ms -> ms.doctor.id == foundDoctor.id }
                .map { ms -> ms.toDto() }
            ResponseEntity.ok(all)
        }
    }

    override suspend fun findById(medicalSlotId: String): ResponseEntity<MedicalSlotResponseDto> {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotFinder.findById(medicalSlotId)
            val dto = foundMedicalSlot.toDto()
            ResponseEntity.ok(dto)
        }
    }

    override suspend fun findById(
        licenseNumber: String,
        state: String,
        medicalSlotId: String
    ): ResponseEntity<MedicalSlotResponseDto> {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotFinder.findById(medicalSlotId)
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state)
            if (foundMedicalSlot.doctor.id != foundDoctor.id) {
                throw InaccessibleMedicalSlotException(licenseNumber, state)
            }
            val dto = foundMedicalSlot.toDto()
            ResponseEntity.ok(dto)
        }
    }
}