package com.api.v1.doctors.services

import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.doctors.dtos.DoctorRegistrationDto
import com.api.v1.doctors.dtos.MedicalLicenseNumber
import com.api.v1.doctors.exceptions.DuplicatedMedicalLicenseNumberException
import com.api.v1.doctors.utils.toDto
import com.api.v1.people.exceptions.DuplicatedEmailException
import com.api.v1.people.exceptions.DuplicatedSsnException
import com.api.v1.people.services.exposed.PersonRegistrationService
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class DoctorRegistrationServiceImpl(
    private val doctorRepository: DoctorRepository,
    private val personRegistrationService: PersonRegistrationService
): DoctorRegistrationService {

    override suspend fun register(registrationDto: @Valid DoctorRegistrationDto) {
        return withContext(Dispatchers.IO) {
            val medicalLicenseNumber = registrationDto.medicalLicenseNumber
            val ssn = registrationDto.personRegistrationDto.ssn
            val email = registrationDto.personRegistrationDto.email
            validate(medicalLicenseNumber, ssn, email)
            val savedPerson = personRegistrationService.register(registrationDto.personRegistrationDto)
            val newDoctor = Doctor.of(savedPerson, registrationDto.medicalLicenseNumber)
            val savedDoctor = doctorRepository.save(newDoctor)
            savedDoctor.toDto()
        }
    }

    private suspend fun validate(medicalLicenseNumber: MedicalLicenseNumber, ssn: String, email: String) {
        val isGivenMedicalLicenseNumberDuplicated = doctorRepository
            .findAll()
            .firstOrNull { c -> c.medicalLicenseNumber == medicalLicenseNumber } != null
        if (isGivenMedicalLicenseNumberDuplicated) {
            throw DuplicatedMedicalLicenseNumberException()
        }

        val isGivenSsnDuplicated = doctorRepository
            .findAll()
            .firstOrNull { c -> c.person.ssn == ssn } != null
        if (isGivenSsnDuplicated) {
            throw DuplicatedSsnException()
        }

        val isGivenEmailDuplicated = doctorRepository
            .findAll()
            .firstOrNull { c -> c.person.email == email } != null
        if (isGivenEmailDuplicated) {
            throw DuplicatedEmailException()
        }
    }
}