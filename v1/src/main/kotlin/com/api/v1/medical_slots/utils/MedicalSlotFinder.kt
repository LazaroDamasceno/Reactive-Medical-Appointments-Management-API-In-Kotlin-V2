package com.api.v1.medical_slots.utils

import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.medical_slots.domain.MedicalSlot
import com.api.v1.medical_slots.domain.MedicalSlotRepository
import com.api.v1.medical_slots.exceptions.NonExistentMedicalSlotException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class MedicalSlotFinder(
    private val medicalSlotRepository: MedicalSlotRepository
) {

    suspend fun findById(id: String): MedicalSlot {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotRepository
                .findAll()
                .firstOrNull{ ms -> ms.id == id }
            if (foundMedicalSlot == null) {
                throw NonExistentMedicalSlotException(id)
            }
            foundMedicalSlot
        }
    }

    suspend fun find(doctor: Doctor, availableAt: LocalDateTime): MedicalSlot? {
        return withContext(Dispatchers.IO) {
            medicalSlotRepository
                .findAll()
                .firstOrNull { ms -> ms.doctor == doctor
                        && ms.availableAt == availableAt
                        && ms.canceledAt == null
                        && ms.completedAt == null
                }
        }
    }
}