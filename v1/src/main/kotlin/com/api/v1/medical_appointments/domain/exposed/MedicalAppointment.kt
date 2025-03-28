package com.api.v1.medical_appointments.domain.exposed

import com.api.v1.customers.domain.exposed.Customer
import com.api.v1.doctors.domain.exposed.Doctor
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
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

}