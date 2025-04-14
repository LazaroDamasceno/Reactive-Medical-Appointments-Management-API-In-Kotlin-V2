package com.api.v2.doctors.dtos.exposed

import com.api.v2.common.States
import jakarta.validation.constraints.NotNull

data class MedicalLicenseNumber(
    val licenseNumber: String,
    @NotNull
    val state: States
)
