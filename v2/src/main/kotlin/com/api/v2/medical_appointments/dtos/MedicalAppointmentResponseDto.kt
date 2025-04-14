package com.api.v2.medical_appointments.dtos

import com.api.v2.customers.dtos.exposed.CustomerResponseDto
import com.api.v2.doctors.dtos.exposed.DoctorResponseDto
import java.time.LocalDateTime

data class MedicalAppointmentResponseDto(
    val customer: CustomerResponseDto,
    val doctor: DoctorResponseDto,
    val bookedAt: LocalDateTime,
    val canceledAt: LocalDateTime?,
    val completedAt: LocalDateTime?
)
