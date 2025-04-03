package com.api.v1.doctors.utils

import com.api.v1.common.States
import com.api.v1.common.toState
import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.dtos.exposed.MedicalLicenseNumber
import com.api.v1.doctors.exceptions.NonExistentDoctorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class DoctorFinder(
    private val doctorRepository: DoctorRepository
) {

    suspend fun findByMedicalLicenseNumber(licenseNumber: String, state: String): Doctor {
        return withContext(Dispatchers.IO) {
            val medicalLicenseNumber = MedicalLicenseNumber(licenseNumber, state.uppercase().toState())
            val foundDoctor = doctorRepository
                .findAll()
                .firstOrNull{ d -> d.medicalLicenseNumber == medicalLicenseNumber }
            if (foundDoctor == null) {
                throw NonExistentDoctorException(licenseNumber, state)
            }
            foundDoctor
        }
    }
}