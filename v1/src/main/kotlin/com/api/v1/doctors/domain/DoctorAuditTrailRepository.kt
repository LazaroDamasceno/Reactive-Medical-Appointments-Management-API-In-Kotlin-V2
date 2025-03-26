package com.api.v1.doctors.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DoctorAuditTrailRepository: CoroutineCrudRepository<Doctor, String>