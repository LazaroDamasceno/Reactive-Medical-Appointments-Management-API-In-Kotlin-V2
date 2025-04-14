package com.api.v2.doctors.domain.exposed

import com.api.v2.doctors.dtos.exposed.DoctorResponseDto
import com.api.v2.doctors.dtos.exposed.MedicalLicenseNumber
import com.api.v2.people.domain.exposed.Person
import com.api.v2.people.utils.formatFullName
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

    fun toDto(): DoctorResponseDto {
        return DoctorResponseDto(
            person.formatFullName(),
            medicalLicenseNumber
        )
    }
}