package com.api.v2.payments.services

import com.api.v2.payments.dto.PaymentResponseDto
import org.springframework.http.ResponseEntity

interface MedicalAppointmentPaymentService {
    suspend fun pay(cardId: String, medicalAppointmentId: String): ResponseEntity<PaymentResponseDto>
}