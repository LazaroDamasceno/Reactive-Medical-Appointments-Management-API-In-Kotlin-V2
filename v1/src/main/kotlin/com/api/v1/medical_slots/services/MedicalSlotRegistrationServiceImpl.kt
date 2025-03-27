package com.api.v1.medical_slots.services

import com.api.v1.common.UnavailableBookingDateTimeException
import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.utils.DoctorFinder
import com.api.v1.medical_slots.domain.MedicalSlot
import com.api.v1.medical_slots.domain.MedicalSlotRepository
import com.api.v1.medical_slots.utils.MedicalSlotFinder
import com.api.v1.medical_slots.utils.toDto
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MedicalSlotRegistrationServiceImpl(
    private val medicalSlotRepository: MedicalSlotRepository,
    private val medicalSlotFinder: MedicalSlotFinder,
    private val doctorFinder: DoctorFinder
): MedicalSlotRegistrationService {

    override suspend fun register(licenseNumber: String, state: String, availableAt: @NotNull LocalDateTime) {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state)
            onDuplicatedBookingDateTime(foundDoctor, availableAt)
            val newMedicalSlot = MedicalSlot.of(foundDoctor, availableAt)
            val savedMedicalSlot = medicalSlotRepository.save(newMedicalSlot)
            savedMedicalSlot.toDto()
        }
    }

    private suspend fun onDuplicatedBookingDateTime(doctor: Doctor, availableAt: LocalDateTime) {
        if (medicalSlotFinder.find(doctor, availableAt) != null) {
            throw UnavailableBookingDateTimeException(availableAt)
        }
    }
}