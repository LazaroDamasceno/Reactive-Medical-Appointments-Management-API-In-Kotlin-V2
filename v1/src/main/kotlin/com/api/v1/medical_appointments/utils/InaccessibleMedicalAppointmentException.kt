package com.api.v1.medical_appointments.utils

class InaccessibleMedicalAppointmentException(licenseNumber: String, state: String)
    : RuntimeException("""
            Doctor whose medical license number is ${licenseNumber}/${state} not associated with sought medical appointment
        """)