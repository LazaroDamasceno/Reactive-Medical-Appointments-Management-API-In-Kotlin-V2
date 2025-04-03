package com.api.v1.doctors.services

import com.api.v1.doctors.domain.DoctorAuditTrail
import com.api.v1.doctors.domain.DoctorAuditTrailRepository
import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.exceptions.ImmutableDoctorException
import com.api.v1.doctors.utils.DoctorFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DoctorManagementServiceImpl(
    private val doctorFinder: DoctorFinder,
    private val doctorRepository: DoctorRepository,
    private val doctorAuditTrailRepository: DoctorAuditTrailRepository
): DoctorManagementService {

    override suspend fun terminate(licenseNumber: String, state: String): ResponseEntity<Unit> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state.uppercase())
            onTerminatedDoctor(foundDoctor)
            val auditTrail = DoctorAuditTrail.of(foundDoctor)
            doctorAuditTrailRepository.save(auditTrail)
            foundDoctor.markAsTerminated()
            doctorRepository.save(foundDoctor)
            ResponseEntity.noContent().build()
        }
    }

    override suspend fun rehire(licenseNumber: String, state: String): ResponseEntity<Unit> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state.uppercase())
            onTerminatedDoctor(foundDoctor)
            val auditTrail = DoctorAuditTrail.of(foundDoctor)
            doctorAuditTrailRepository.save(auditTrail)
            foundDoctor.markAsRehired()
            doctorRepository.save(foundDoctor)
            ResponseEntity.noContent().build()
        }
    }

    private fun onTerminatedDoctor(doctor: Doctor) {
        if (doctor.terminatedAt != null) {
            val licenseNumber = doctor.medicalLicenseNumber.licenseNumber
            val state = doctor.medicalLicenseNumber.state.toString()
            val message = "Doctor whose medical license number is ${licenseNumber}/${state} is already terminated."
            throw ImmutableDoctorException(message)
        }
    }

    private fun onActiveDoctor(doctor: Doctor) {
        if (doctor.terminatedAt == null) {
            val licenseNumber = doctor.medicalLicenseNumber.licenseNumber
            val state = doctor.medicalLicenseNumber.state.toString()
            val message = "Doctor whose medical license number is ${licenseNumber}/${state} is active."
            throw ImmutableDoctorException(message)
        }
    }

}