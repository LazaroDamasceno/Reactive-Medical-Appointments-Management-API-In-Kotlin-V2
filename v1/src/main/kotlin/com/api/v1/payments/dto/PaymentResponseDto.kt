package com.api.v1.payments.dto

import com.api.v1.cards.dtos.CardResponseDto
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto

data class PaymentResponseDto(
    val id: String,
    val medicalAppointment: MedicalAppointmentResponseDto,
    val card: CardResponseDto
)
