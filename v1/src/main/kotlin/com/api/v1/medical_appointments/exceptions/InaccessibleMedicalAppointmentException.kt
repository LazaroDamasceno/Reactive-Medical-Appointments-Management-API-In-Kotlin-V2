package com.api.v1.medical_appointments.exceptions

class InaccessibleMedicalAppointmentException : RuntimeException {

    constructor(customerId: String) : super("""
        Customer whose id is $customerId not associated with sought medical appointment.
    """)

    constructor() : super("""
        Doctor is already the medical slot's doctor of the sought medical appointment.
    """)

}