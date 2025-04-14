package com.api.v2.payments.dto

import com.api.v2.cards.dtos.CardResponseDto
import com.api.v2.medical_appointments.dtos.MedicalAppointmentResponseDto

data class PaymentResponseDto(
    val id: String,
    val medicalAppointment: MedicalAppointmentResponseDto,
    val card: CardResponseDto
)
