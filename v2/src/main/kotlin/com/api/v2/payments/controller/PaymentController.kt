package com.api.v2.payments.controller

import com.api.v2.payments.dto.PaymentResponseDto
import com.api.v2.payments.services.MedicalAppointmentPaymentService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v2/payments")
class PaymentController(
    private val paymentService: MedicalAppointmentPaymentService
): MedicalAppointmentPaymentService {

    @Operation(summary = "Pay a medical appointment")
    @PostMapping("{cardId}/{medicalAppointmentId}")
    override suspend fun pay(@PathVariable cardId: String,
                             @PathVariable medicalAppointmentId: String
    ): ResponseEntity<PaymentResponseDto> {
        return paymentService.pay(cardId, medicalAppointmentId)
    }
}