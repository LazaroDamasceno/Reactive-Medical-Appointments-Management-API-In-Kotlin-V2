package com.api.v1.medical_slots.dtos

import com.api.v1.doctors.dtos.DoctorResponseDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class MedicalSlotResponseDto(
    @Valid
    val doctor: DoctorResponseDto,
    @NotNull
    val availableAt: LocalDateTime,
    val canceledAt: LocalDateTime?,
    val completedAt: LocalDateTime?
)
