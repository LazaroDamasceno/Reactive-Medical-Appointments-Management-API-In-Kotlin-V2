package com.api.v1.medical_appointments.services.exposed

import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment

interface MedicalAppointmentUpdatingService{
    suspend fun set(medicalAppointment: MedicalAppointment): MedicalAppointment
}