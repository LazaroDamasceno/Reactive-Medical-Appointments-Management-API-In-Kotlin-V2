package com.api.v1.medical_slots.utils

import com.api.v1.doctors.utils.toDto
import com.api.v1.medical_slots.domain.MedicalSlot
import com.api.v1.medical_slots.dtos.MedicalSlotResponseDto

fun MedicalSlot.toDto(): MedicalSlotResponseDto {
    return MedicalSlotResponseDto(
        doctor.toDto(),
        availableAt,
        canceledAt,
        completedAt
    )
}