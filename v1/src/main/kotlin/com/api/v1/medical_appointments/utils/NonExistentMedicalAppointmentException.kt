package com.api.v1.medical_appointments.utils

class NonExistentMedicalAppointmentException(id: String)
    : RuntimeException("Medical appointment whose id is $id was not found.")