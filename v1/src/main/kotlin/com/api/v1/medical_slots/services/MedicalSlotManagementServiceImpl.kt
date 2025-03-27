package com.api.v1.medical_slots.services

import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.utils.DoctorFinder
import com.api.v1.medical_slots.domain.MedicalSlot
import com.api.v1.medical_slots.domain.MedicalSlotRepository
import com.api.v1.medical_slots.exceptions.InaccessibleMedicalSlotException
import com.api.v1.medical_slots.utils.MedicalSlotFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MedicalSlotManagementServiceImpl(
    private val doctorFinder: DoctorFinder,
    private val medicalSlotFinder: MedicalSlotFinder,
    private val medicalSlotRepository: MedicalSlotRepository
): MedicalSlotManagementService {

    override suspend fun cancel(licenseNumber: String, state: String, medicalSlotId: String) {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state)
            val foundMedicalSlot = medicalSlotFinder.findById(medicalSlotId)
            onNonAssociatedDoctorWithMedicalSlot(foundDoctor, foundMedicalSlot)
            foundMedicalSlot.markAsCanceled()
            medicalSlotRepository.save(foundMedicalSlot)
        }
    }

    override suspend fun completed(licenseNumber: String, state: String, medicalSlotId: String) {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state)
            val foundMedicalSlot = medicalSlotFinder.findById(medicalSlotId)
            onNonAssociatedDoctorWithMedicalSlot(foundDoctor, foundMedicalSlot)
            foundMedicalSlot.markAsCompleted()
            medicalSlotRepository.save(foundMedicalSlot)
        }
    }

    private suspend fun onNonAssociatedDoctorWithMedicalSlot(doctor: Doctor, medicalSlot: MedicalSlot) {
        if (medicalSlot.doctor.id == doctor.id) {
            val licenseNumber = doctor.medicalLicenseNumber.licenseNumber
            val state = doctor.medicalLicenseNumber.state
            throw InaccessibleMedicalSlotException(licenseNumber, state.toString())
        }
    }
}