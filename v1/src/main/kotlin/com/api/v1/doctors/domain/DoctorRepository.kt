package com.api.v1.doctors.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DoctorRepository: CoroutineCrudRepository<Doctor, String>