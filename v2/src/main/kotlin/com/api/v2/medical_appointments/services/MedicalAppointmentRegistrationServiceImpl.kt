package com.api.v2.medical_appointments.services

import com.api.v2.common.PastBookingDateChecker
import com.api.v2.common.PastBookingDateException
import com.api.v2.common.UnavailableBookingDateTimeException
import com.api.v2.customers.domain.exposed.Customer
import com.api.v2.customers.utils.CustomerFinder
import com.api.v2.doctors.domain.exposed.Doctor
import com.api.v2.doctors.utils.DoctorFinder
import com.api.v2.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v2.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v2.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v2.medical_appointments.exceptions.InaccessibleMedicalAppointmentException
import com.api.v2.medical_appointments.utils.MedicalAppointmentFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MedicalAppointmentRegistrationServiceImpl(
    private val doctorFinder: DoctorFinder,
    private val customerFinder: CustomerFinder,
    private val medicalAppointmentFinder: MedicalAppointmentFinder,
    private val medicalAppointmentRepository: MedicalAppointmentRepository
): MedicalAppointmentRegistrationService {

    override suspend fun register(licenseNumber: String,
                                  state: String,
                                  customerId: String,
                                  bookedAt: LocalDateTime
    ): ResponseEntity<MedicalAppointmentResponseDto> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state.uppercase())
            val foundCustomer = customerFinder.findById(customerId)
            validate(foundCustomer, foundDoctor, bookedAt)
            val newMedicalAppointment = MedicalAppointment.of(foundCustomer, foundDoctor, bookedAt)
            val savedMedicalAppointment = medicalAppointmentRepository.save(newMedicalAppointment)
            val dto = savedMedicalAppointment.toDto()
            ResponseEntity.status(HttpStatus.CREATED).body(dto)
        }
    }

    private suspend fun validate(customer: Customer, doctor: Doctor, bookedAt: LocalDateTime) {
        if (medicalAppointmentFinder.find(customer, bookedAt) != null) {
            throw UnavailableBookingDateTimeException(bookedAt)
        }

        if (PastBookingDateChecker.isBeforeToday(bookedAt.toLocalDate())) {
            throw PastBookingDateException()
        }

        if (doctor.person.id == customer.id) {
            throw InaccessibleMedicalAppointmentException()
        }
    }
}