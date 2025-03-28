package com.api.v1.medical_appointments.services

import com.api.v1.common.UnavailableBookingDateTimeException
import com.api.v1.customers.domain.exposed.Customer
import com.api.v1.customers.utils.CustomerFinder
import com.api.v1.doctors.utils.DoctorFinder
import com.api.v1.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v1.medical_appointments.utils.MedicalAppointmentFinder
import com.api.v1.medical_appointments.utils.toDto
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
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state)
            val foundCustomer = customerFinder.findById(customerId)
            onGivenBookingDateTimeDuplicated(foundCustomer, bookedAt)
            val newMedicalAppointment = MedicalAppointment.of(foundCustomer, foundDoctor, bookedAt)
            val savedMedicalAppointment = medicalAppointmentRepository.save(newMedicalAppointment)
            val dto = savedMedicalAppointment.toDto()
            ResponseEntity.status(HttpStatus.CREATED).body(dto)
        }
    }

    private suspend fun onGivenBookingDateTimeDuplicated(customer: Customer, bookedAt: LocalDateTime) {
       if (medicalAppointmentFinder.find(customer, bookedAt) != null) {
           throw UnavailableBookingDateTimeException(bookedAt)
       }
    }
}