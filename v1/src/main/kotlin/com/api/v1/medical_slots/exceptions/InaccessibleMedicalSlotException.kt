package com.api.v1.medical_slots.exceptions

class InaccessibleMedicalSlotException(licenseNumber: String, state: String)
    : RuntimeException("Doctor whose medical license Number is $licenseNumber/$state is not the medical slot's doctor.")