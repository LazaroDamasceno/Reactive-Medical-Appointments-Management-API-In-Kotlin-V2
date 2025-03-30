package com.api.v1.medical_appointments.exceptions

class InaccessibleMedicalAppointmentException(id: String)
    : RuntimeException("""
            Customer whose id is $id not associated with sought medical appointment.
        """)