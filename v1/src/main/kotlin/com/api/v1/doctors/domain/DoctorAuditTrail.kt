package com.api.v1.doctors.domain

import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
data class DoctorAuditTrail(
    @BsonId
    val id: String,
    val doctor: Doctor,
    val createdAt: LocalDateTime
) {

    companion object {
        fun of(doctor: Doctor): DoctorAuditTrail {
            return DoctorAuditTrail(
                UUID.randomUUID().toString(),
                doctor,
                LocalDateTime.now()
            )
        }
    }
}
