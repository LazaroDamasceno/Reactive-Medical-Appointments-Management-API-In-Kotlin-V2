package com.api.v2.payments.domain

import com.api.v2.cards.domain.exposed.Card
import com.api.v2.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v2.payments.dto.PaymentResponseDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
data class Payment (
    @Id
    val id: String,
    val medicalAppointment: MedicalAppointment,
    val card: Card,
    val createdAt: LocalDateTime
) {

    companion object {
        fun of(medicalAppointment: MedicalAppointment, card: Card): Payment {
            return Payment(
                UUID.randomUUID().toString(),
                medicalAppointment,
                card,
                LocalDateTime.now()
            )
        }
    }

    fun toDto(): PaymentResponseDto {
        return PaymentResponseDto(
            id,
            medicalAppointment.toDto(),
            card.toDto()
        )
    }
}