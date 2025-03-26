package com.api.v1.doctors.exceptions

class NonExistentDoctorException(licenseNumber: String, state: String)
    : RuntimeException("Doctor whose medical license number is ${licenseNumber}/${state} was not found.")