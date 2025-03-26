package com.api.v1.doctors.exceptions

class NonExistentDoctorException(medicalLicenseNumber: String)
    : RuntimeException("Doctor whose medical license number is $medicalLicenseNumber was not found.")