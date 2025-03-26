package com.api.v1.medical_slots.exceptions

class InaccessibleMedicalSlotException(medicalLicenseNumber: String)
    : RuntimeException("Doctor whose medical license Number is $medicalLicenseNumber is not the medical slot's doctor.")