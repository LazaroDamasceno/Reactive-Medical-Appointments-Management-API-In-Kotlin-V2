package com.api.v1.medical_appointments.services

import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_appointments.services.exposed.MedicalAppointmentUpdatingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MedicalAppointmentUpdatingServiceImpl(
    private val medicalAppointmentRepository: MedicalAppointmentRepository
): MedicalAppointmentUpdatingService {

    override suspend fun set(medicalAppointment: MedicalAppointment): MedicalAppointment {
        return withContext(Dispatchers.IO) {
            medicalAppointmentRepository.save(medicalAppointment)
        }
    }
}