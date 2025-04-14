package com.api.v1.payments.services

import com.api.v1.cards.utils.CardFinder
import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_appointments.exceptions.ImmutableMedicalAppointmentException
import com.api.v1.medical_appointments.services.exposed.MedicalAppointmentUpdatingService
import com.api.v1.medical_appointments.utils.MedicalAppointmentFinder
import com.api.v1.payments.domain.Payment
import com.api.v1.payments.domain.PaymentRepository
import com.api.v1.payments.dto.PaymentResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MedicalAppointmentPaymentServiceImpl(
    private val paymentRepository: PaymentRepository,
    private val cardFinder: CardFinder,
    private val medicalAppointmentFinder: MedicalAppointmentFinder,
    private val medicalAppointmentUpdatingService: MedicalAppointmentUpdatingService
): MedicalAppointmentPaymentService {

    override suspend fun pay(cardId: String, medicalAppointmentId: String): ResponseEntity<PaymentResponseDto> {
       return withContext(Dispatchers.IO) {
           val foundCard = cardFinder.findById(cardId)
           val foundMedicalAppointment = medicalAppointmentFinder.findById(medicalAppointmentId)
            validate(foundMedicalAppointment)
           foundMedicalAppointment.markAsPaid()
           val paidMedicalAppointment = medicalAppointmentUpdatingService.set(foundMedicalAppointment)
           val payment = Payment.of(paidMedicalAppointment, foundCard)
           val savedPayment = paymentRepository.save(payment)
           val dto = savedPayment.toDto()
           ResponseEntity.status(HttpStatus.CREATED).body(dto)
       }
    }

    private fun validate(medicalAppointment: MedicalAppointment) {
        if (medicalAppointment.completedAt != null && medicalAppointment.paidAt != null) {
            val message = "Medical appointment whose id is ${medicalAppointment.id} is already paid."
            throw ImmutableMedicalAppointmentException(message)
        }

        if (medicalAppointment.canceledAt != null && medicalAppointment.completedAt == null) {
            val message = "Medical appointment whose id is ${medicalAppointment.id} is already canceled."
            throw ImmutableMedicalAppointmentException(message)
        }

        if (medicalAppointment.canceledAt == null && medicalAppointment.completedAt != null) {
            val message = "Medical appointment whose id is ${medicalAppointment.id} is already completed."
            throw ImmutableMedicalAppointmentException(message)
        }
    }
}