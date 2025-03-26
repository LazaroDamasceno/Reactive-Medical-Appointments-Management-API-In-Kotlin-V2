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
class Doctor {

    @BsonId
    var id: String = UUID.randomUUID().toString()
    lateinit var person: Person
    lateinit var medicalLicenseNumber: MedicalLicenseNumber
    var createdAt: LocalDateTime = LocalDateTime.now()
    var terminatedAt: LocalDateTime? = null

    constructor()

    private constructor(person: Person, medicalLicenseNumber: MedicalLicenseNumber) {
        this.person = person
        this.medicalLicenseNumber = medicalLicenseNumber
    }

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