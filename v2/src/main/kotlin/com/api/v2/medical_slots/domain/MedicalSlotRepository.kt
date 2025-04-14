package com.api.v2.medical_slots.domain

import com.api.v2.medical_slots.domain.exposed.MedicalSlot
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MedicalSlotRepository: CoroutineCrudRepository<MedicalSlot, String>