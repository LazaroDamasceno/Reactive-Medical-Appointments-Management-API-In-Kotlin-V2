package com.api.v1.doctors.exceptions

class DuplicatedMedicalLicenseNumberException: RuntimeException("Given medical license number is already in use.")