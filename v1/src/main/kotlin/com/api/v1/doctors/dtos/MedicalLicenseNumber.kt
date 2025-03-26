package com.api.v1.doctors.dtos

import com.api.v1.common.States
import jakarta.validation.constraints.NotNull

data class MedicalLicenseNumber(
    val licenseNumber: String,
    @NotNull
    val state: States
)
