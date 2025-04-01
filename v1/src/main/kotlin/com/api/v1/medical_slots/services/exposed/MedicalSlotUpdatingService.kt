package com.api.v1.medical_slots.services.exposed

import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import com.api.v1.medical_slots.domain.MedicalSlot

interface MedicalSlotUpdatingService {
    suspend fun set(medicalSlot: MedicalSlot)
    suspend fun set(medicalSlot: MedicalSlot, medicalAppointment: MedicalAppointment)
}