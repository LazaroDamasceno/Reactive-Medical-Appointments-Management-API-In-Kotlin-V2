package com.api.v1.doctors.domain

import com.api.v1.doctors.domain.exposed.Doctor
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DoctorRepository: CoroutineCrudRepository<Doctor, String>