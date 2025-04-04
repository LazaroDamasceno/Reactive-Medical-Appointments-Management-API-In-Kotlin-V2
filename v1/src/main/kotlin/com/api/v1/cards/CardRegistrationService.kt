package com.api.v1.cards

import java.time.LocalDate

interface CardRegistrationService {
    suspend fun creditCard(cvc: String, dueDate: LocalDate): CardResponseDto
    suspend fun debitCard( cvc: String, dueDate: LocalDate): CardResponseDto
}