package com.api.v2.doctors.services

import com.api.v2.doctors.domain.DoctorRepository
import com.api.v2.doctors.dtos.exposed.DoctorResponseDto
import com.api.v2.doctors.utils.DoctorFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DoctorRetrievalServiceImpl(
    private val doctorFinder: DoctorFinder,
    private val doctorRepository: DoctorRepository
): DoctorRetrievalService {

    override suspend fun findByMedicalLicenseNumber(
        licenseNumber: String,
        state: String
    ): ResponseEntity<DoctorResponseDto> {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinder.findByMedicalLicenseNumber(licenseNumber, state.uppercase())
            val dto = foundDoctor.toDto()
            ResponseEntity.ok(dto)
        }
    }

    override suspend fun findAll(): ResponseEntity<Flow<DoctorResponseDto>> {
        return withContext(Dispatchers.IO) {
            val all = doctorRepository
                .findAll()
                .map { d -> d.toDto() }
            ResponseEntity.ok(all)
        }
    }
}