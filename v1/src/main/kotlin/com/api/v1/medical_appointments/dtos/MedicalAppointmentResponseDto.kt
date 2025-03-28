package com.api.v1.medical_appointments.dtos

import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.doctors.dtos.exposed.DoctorResponseDto
import java.time.LocalDateTime

data class MedicalAppointmentResponseDto(
    val customer: CustomerResponseDto,
    val doctor: DoctorResponseDto,
    val bookedAt: LocalDateTime,
    val canceledAt: LocalDateTime?,
    val completedAt: LocalDateTime?
)
