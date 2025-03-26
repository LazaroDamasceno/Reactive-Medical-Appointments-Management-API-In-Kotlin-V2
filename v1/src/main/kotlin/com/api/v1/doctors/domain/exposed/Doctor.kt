package com.api.v1.doctors.domain.exposed

import com.api.v1.doctors.dtos.DoctorRegistrationDto
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.dtos.MedicalLicenseNumber
import com.api.v1.people.domain.exposed.Person
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
class Doctor(
    var person: Person,
    var medicalLicenseNumber: MedicalLicenseNumber
) {

    @BsonId
    var id: String = UUID.randomUUID().toString()

    var createdAt: LocalDateTime = LocalDateTime.now()
    var terminatedAt: LocalDateTime? = null

    companion object {
        fun of(person: Person, medicalLicenseNumber: MedicalLicenseNumber): Doctor {
            return Doctor(person, medicalLicenseNumber)
        }
    }

    fun markAsTerminated() {
        terminatedAt = LocalDateTime.now()
    }

    fun markAsRehired() {
        terminatedAt = null
    }
}