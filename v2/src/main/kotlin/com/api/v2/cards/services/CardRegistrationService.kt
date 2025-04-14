package com.api.v2.cards.services

import com.api.v2.cards.dtos.CardResponseDto
import org.springframework.http.ResponseEntity
import java.time.LocalDate

interface CardRegistrationService {
    suspend fun creditCard(cvc: String, dueDate: LocalDate): ResponseEntity<CardResponseDto>
    suspend fun debitCard( cvc: String, dueDate: LocalDate): ResponseEntity<CardResponseDto>
}