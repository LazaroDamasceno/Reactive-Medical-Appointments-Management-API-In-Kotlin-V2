package com.api.v1.medical_appointments.utils

import com.api.v1.customers.utils.toDto
import com.api.v1.doctors.utils.toDto
import com.api.v1.medical_appointments.domain.MedicalAppointment
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto

fun MedicalAppointment.toDto(): MedicalAppointmentResponseDto {
    return MedicalAppointmentResponseDto(
        customer.toDto(),
        doctor.toDto(),
        bookedAt,
        canceledAt,
        completedAt
    )
}