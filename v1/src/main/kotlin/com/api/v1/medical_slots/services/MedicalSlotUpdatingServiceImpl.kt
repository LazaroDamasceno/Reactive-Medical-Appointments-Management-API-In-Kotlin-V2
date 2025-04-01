package com.api.v1.medical_slots.services

import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_slots.domain.MedicalSlot
import com.api.v1.medical_slots.domain.MedicalSlotRepository
import com.api.v1.medical_slots.services.exposed.MedicalSlotUpdatingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MedicalSlotUpdatingServiceImpl(
    private val medicalSlotRepository: MedicalSlotRepository
): MedicalSlotUpdatingService {

    override suspend fun set(medicalSlot: MedicalSlot) {
        return withContext(Dispatchers.IO) {
            medicalSlotRepository.save(medicalSlot)
        }
    }

    override suspend fun set(medicalSlot: MedicalSlot, medicalAppointment: MedicalAppointment) {
        return withContext(Dispatchers.IO) {
            medicalSlot.medicalAppointment = medicalAppointment
            medicalSlotRepository.save(medicalSlot)
        }
    }
}