package com.api.v2.medical_appointments.exceptions

class NonExistentMedicalAppointmentException(id: String)
    : RuntimeException("Medical appointment whose id is $id was not found.")