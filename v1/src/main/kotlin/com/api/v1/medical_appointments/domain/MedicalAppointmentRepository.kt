package com.api.v1.medical_appointments.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MedicalAppointmentRepository: CoroutineCrudRepository<MedicalAppointment, String>