package com.api.v1.medical_slots.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MedicalSlotRepository: CoroutineCrudRepository<MedicalSlot, String>