package com.api.v1.medical_slots.services

import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.utils.DoctorFinder
import com.api.v1.medical_appointments.services.exposed.MedicalAppointmentUpdatingService
import com.api.v1.medical_slots.domain.exposed.MedicalSlot
import com.api.v1.medical_slots.domain.MedicalSlotRepository
import com.api.v1.medical_slots.exceptions.ImmutableMedicalSlotException
import com.api.v1.medical_slots.exceptions.InaccessibleMedicalSlotException
import com.api.v1.medical_slots.utils.MedicalSlotFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class MedicalSlotManagementServiceImpl(
    private val doctorFinder: DoctorFinder,
    private val medicalSlotFinder: MedicalSlotFinder,
    private val medicalSlotRepository: MedicalSlotRepository,
    private val medicalAppointmentUpdatingService: MedicalAppointmentUpdatingService
): MedicalSlotManagementService {

    override suspend fun cancel(licenseNumber: String, state: String, medicalSlotId: String): ResponseEntity<Unit> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state.uppercase())
            val foundMedicalSlot = medicalSlotFinder.findById(medicalSlotId)
            validate(foundDoctor, foundMedicalSlot)
            foundMedicalSlot.markAsCanceled()
            val medicalAppointment = foundMedicalSlot.medicalAppointment
            medicalAppointment!!.markAsCanceled()
            val canceledMedicalAppointment = medicalAppointmentUpdatingService.set(medicalAppointment)
            foundMedicalSlot.medicalAppointment = canceledMedicalAppointment
            medicalSlotRepository.save(foundMedicalSlot)
            ResponseEntity.noContent().build()
        }
    }

    override suspend fun completed(licenseNumber: String, state: String, medicalSlotId: String, price: Double): ResponseEntity<Unit> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state.uppercase())
            val foundMedicalSlot = medicalSlotFinder.findById(medicalSlotId)
            validate(foundDoctor, foundMedicalSlot)
            foundMedicalSlot.markAsCompleted()
            val medicalAppointment = foundMedicalSlot.medicalAppointment
            medicalAppointment!!.price = BigDecimal.valueOf(price)
            medicalAppointment.markAsCompleted()
            val completedMedicalAppointment = medicalAppointmentUpdatingService.set(medicalAppointment)
            foundMedicalSlot.medicalAppointment = completedMedicalAppointment
            medicalSlotRepository.save(foundMedicalSlot)
            ResponseEntity.noContent().build()
        }
    }

    private fun validate(doctor: Doctor, medicalSlot: MedicalSlot) {
        if (medicalSlot.doctor.id != doctor.id) {
            val licenseNumber = doctor.medicalLicenseNumber.licenseNumber
            val state = doctor.medicalLicenseNumber.state
            throw InaccessibleMedicalSlotException(licenseNumber, state.toString())
        }

        if (medicalSlot.canceledAt != null && medicalSlot.completedAt == null) {
            val message = "Medical slot whose id is ${medicalSlot.id} is already canceled."
            throw ImmutableMedicalSlotException(message)
        }


        if (medicalSlot.canceledAt == null && medicalSlot.completedAt != null) {
            val message = "Medical slot whose id is ${medicalSlot.id} is already completed."
            throw ImmutableMedicalSlotException(message)
        }
    }
}