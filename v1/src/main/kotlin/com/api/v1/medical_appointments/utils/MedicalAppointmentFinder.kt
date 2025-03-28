package com.api.v1.medical_appointments.utils

import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_appointments.exceptions.NonExistentMedicalAppointmentException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class MedicalAppointmentFinder(
    private val medicalAppointmentRepository: MedicalAppointmentRepository
) {

    suspend fun findById(id: String): MedicalAppointment {
        return withContext(Dispatchers.IO) {
            val foundMedicalAppointment = medicalAppointmentRepository
                .findById(id)
            if (foundMedicalAppointment == null) {
                throw NonExistentMedicalAppointmentException(id)
            }
            foundMedicalAppointment
        }
    }
}