package com.api.v2.medical_slots.utils

import com.api.v2.doctors.domain.exposed.Doctor
import com.api.v2.medical_slots.domain.exposed.MedicalSlot
import com.api.v2.medical_slots.domain.MedicalSlotRepository
import com.api.v2.medical_slots.exceptions.NonExistentMedicalSlotException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class MedicalSlotFinder(
    private val medicalSlotRepository: MedicalSlotRepository
) {

    suspend fun findById(id: String): MedicalSlot {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotRepository
                .findById(id)
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
                .firstOrNull {
                    ms -> ms.doctor == doctor
                    && ms.availableAt == availableAt
                    && ms.canceledAt == null
                    && ms.completedAt == null
                }
        }
    }
}