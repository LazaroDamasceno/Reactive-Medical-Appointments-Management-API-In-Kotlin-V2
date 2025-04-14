package com.api.v2.medical_slots.domain.exposed

import com.api.v2.doctors.domain.exposed.Doctor
import com.api.v2.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v2.medical_slots.dtos.MedicalSlotResponseDto
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class MedicalSlot(
    var doctor: Doctor,
    var availableAt: LocalDateTime
) {

    @BsonId
    var id: String = UUID.randomUUID().toString()
    var medicalAppointment: MedicalAppointment? = null
    var createdAt: LocalDateTime = LocalDateTime.now()
    var canceledAt: LocalDateTime? = null
    var completedAt: LocalDateTime? = null

    companion object {
        fun of(doctor: Doctor, availableAt: LocalDateTime): MedicalSlot {
            return MedicalSlot(doctor, availableAt)
        }
    }

    fun markAsCanceled() {
        canceledAt = LocalDateTime.now()
    }

    fun markAsCompleted() {
        completedAt = LocalDateTime.now()
    }

    fun toDto(): MedicalSlotResponseDto {
        return MedicalSlotResponseDto(
            doctor.toDto(),
            availableAt,
            canceledAt,
            completedAt
        )
    }
}