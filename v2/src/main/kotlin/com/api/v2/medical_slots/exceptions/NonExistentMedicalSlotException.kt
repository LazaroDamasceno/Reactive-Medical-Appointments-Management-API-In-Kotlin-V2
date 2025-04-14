package com.api.v2.medical_slots.exceptions

class NonExistentMedicalSlotException(id: String)
    : RuntimeException("Medical slot whose id is $id was not found.")