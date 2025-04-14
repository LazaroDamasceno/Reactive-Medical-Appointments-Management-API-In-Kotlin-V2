package com.api.v2.doctors.domain

import com.api.v2.doctors.domain.exposed.Doctor
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DoctorAuditTrailRepository: CoroutineCrudRepository<DoctorAuditTrail, String>