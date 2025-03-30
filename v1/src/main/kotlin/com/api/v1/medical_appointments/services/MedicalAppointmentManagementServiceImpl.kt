package com.api.v1.medical_appointments.services

import com.api.v1.customers.domain.exposed.Customer
import com.api.v1.customers.utils.CustomerFinder
import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_appointments.exceptions.ImmutableMedicalAppointmentException
import com.api.v1.medical_appointments.exceptions.InaccessibleMedicalAppointmentException
import com.api.v1.medical_appointments.utils.MedicalAppointmentFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MedicalAppointmentManagementServiceImpl(
    private val medicalAppointmentRepository: MedicalAppointmentRepository,
    private val medicalAppointmentFinder: MedicalAppointmentFinder,
    private val customerFinder: CustomerFinder
): MedicalAppointmentManagementService {

    override suspend fun cancel(customerId: String, medicalAppointmentId: String): ResponseEntity<Unit> {
        return withContext(Dispatchers.IO) {
            val foundCustomer = customerFinder.findById(customerId)
            val foundMedicalAppointment = medicalAppointmentFinder.findById(medicalAppointmentId)
            validate(foundMedicalAppointment, foundCustomer)
            foundMedicalAppointment.markAsCanceled()
            medicalAppointmentRepository.save(foundMedicalAppointment)
            ResponseEntity.noContent().build()
        }
    }

    override suspend fun complete(customerId: String, medicalAppointmentId: String): ResponseEntity<Unit> {
        return withContext(Dispatchers.IO) {
            val foundCustomer = customerFinder.findById(customerId)
            val foundMedicalAppointment = medicalAppointmentFinder.findById(medicalAppointmentId)
            validate(foundMedicalAppointment, foundCustomer)
            foundMedicalAppointment.markAsCompleted()
            medicalAppointmentRepository.save(foundMedicalAppointment)
            ResponseEntity.noContent().build()
        }
    }


    private fun validate(medicalAppointment: MedicalAppointment, customer: Customer) {
        if (medicalAppointment.customer.id == customer.id) {
            throw InaccessibleMedicalAppointmentException(customer.id)
        }

        if (medicalAppointment.canceledAt != null && medicalAppointment.completedAt == null) {
            val message = "Medical appointment whose id is ${medicalAppointment.id} is already canceled."
            throw ImmutableMedicalAppointmentException(message)
        }

        if (medicalAppointment.canceledAt == null && medicalAppointment.completedAt != null) {
            val message = "Medical appointment whose id is ${medicalAppointment.id} is already completed."
            throw ImmutableMedicalAppointmentException(message)
        }
    }

}