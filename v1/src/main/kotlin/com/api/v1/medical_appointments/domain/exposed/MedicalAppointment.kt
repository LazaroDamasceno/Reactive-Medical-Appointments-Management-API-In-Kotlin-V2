package com.api.v1.medical_appointments.domain.exposed

import com.api.v1.customers.domain.exposed.Customer
import com.api.v1.doctors.domain.exposed.Doctor
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Document
class MedicalAppointment(
    var customer: Customer,
    var doctor: Doctor,
    var bookedAt: LocalDateTime
) {

    @BsonId
    var id: String = UUID.randomUUID().toString()
    var createdAt: LocalDateTime = LocalDateTime.now()
    var canceledAt: LocalDateTime? = null
    var completedAt: LocalDateTime? = null
    var paidAt: LocalDateTime? = null
    var price: BigDecimal? = null

    companion object {
        fun of(customer: Customer, doctor: Doctor, bookedAt: LocalDateTime): MedicalAppointment {
            return MedicalAppointment(customer, doctor, bookedAt)
        }
    }

    fun markAsCanceled() {
        canceledAt = LocalDateTime.now()
    }

    fun markAsCompleted() {
        completedAt = LocalDateTime.now()
    }

    fun markAsPaid() {
        paidAt = LocalDateTime.now()
    }

    fun toDto(): MedicalAppointmentResponseDto {
        return MedicalAppointmentResponseDto(
            customer.toDto(),
            doctor.toDto(),
            bookedAt,
            canceledAt,
            completedAt
        )
    }

}