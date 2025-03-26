package com.api.v1.medical_slots.domain

import com.api.v1.doctors.domain.exposed.Doctor
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
}