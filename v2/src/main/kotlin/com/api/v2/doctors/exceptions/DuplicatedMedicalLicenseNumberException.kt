package com.api.v2.doctors.exceptions

class DuplicatedMedicalLicenseNumberException: RuntimeException("Given medical license number is already in use.")