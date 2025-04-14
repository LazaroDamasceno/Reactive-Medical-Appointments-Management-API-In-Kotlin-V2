package com.api.v2.medical_appointments.utils

import com.api.v2.customers.domain.exposed.Customer
import com.api.v2.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v2.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v2.medical_appointments.exceptions.NonExistentMedicalAppointmentException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import java.time.LocalDateTime

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

    suspend fun find(customer: Customer, bookedAt: LocalDateTime): MedicalAppointment? {
        return withContext(Dispatchers.IO) {
            medicalAppointmentRepository
                .findAll()
                .firstOrNull {
                    ma -> ma.customer.id == customer.id
                    && ma.bookedAt == bookedAt
                    && ma.canceledAt == null
                    && ma.completedAt == null
                }
        }
    }
}