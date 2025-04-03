package com.api.v1.medical_slots.services

import com.api.v1.common.PastBookingDateTimeChecker
import com.api.v1.common.PastBookingDateTimeException
import com.api.v1.common.UnavailableBookingDateTimeException
import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.utils.DoctorFinder
import com.api.v1.medical_slots.domain.exposed.MedicalSlot
import com.api.v1.medical_slots.domain.MedicalSlotRepository
import com.api.v1.medical_slots.dtos.MedicalSlotResponseDto
import com.api.v1.medical_slots.utils.MedicalSlotFinder
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MedicalSlotRegistrationServiceImpl(
    private val medicalSlotRepository: MedicalSlotRepository,
    private val medicalSlotFinder: MedicalSlotFinder,
    private val doctorFinder: DoctorFinder
): MedicalSlotRegistrationService {

    override suspend fun register(licenseNumber: String,
                                  state: String,
                                  availableAt: @NotNull LocalDateTime
    ): ResponseEntity<MedicalSlotResponseDto> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state.uppercase())
            validate(foundDoctor, availableAt)
            val newMedicalSlot = MedicalSlot.of(foundDoctor, availableAt)
            val savedMedicalSlot = medicalSlotRepository.save(newMedicalSlot)
            val dto = savedMedicalSlot.toDto()
            ResponseEntity.status(HttpStatus.CREATED).body(dto)
        }
    }

    private suspend fun validate(doctor: Doctor, availableAt: LocalDateTime) {
        if (medicalSlotFinder.find(doctor, availableAt) != null) {
            throw UnavailableBookingDateTimeException(availableAt)
        }

        if (PastBookingDateTimeChecker.isBeforeToday(availableAt.toLocalDate())) {
            throw PastBookingDateTimeException()
        }
    }
}