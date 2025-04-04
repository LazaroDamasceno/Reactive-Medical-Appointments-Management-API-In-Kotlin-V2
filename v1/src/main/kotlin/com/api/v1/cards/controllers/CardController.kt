package com.api.v1.cards.controllers

import com.api.v1.cards.dtos.CardResponseDto
import com.api.v1.cards.services.CardDeletionService
import com.api.v1.cards.services.CardRegistrationService
import com.api.v1.cards.services.CardRetrievalService
import io.swagger.v3.oas.annotations.Operation
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("api/v1/cards")
class CardController(
    val registrationService: CardRegistrationService,
    val deletionService: CardDeletionService,
    val retrievalService: CardRetrievalService,
) {

    @Operation(summary = "Create a credit card")
    @PostMapping("credit-card/{cvc}/{dueDate}")
    suspend fun creditCard(@PathVariable cvc: String, @PathVariable dueDate: LocalDate): ResponseEntity<CardResponseDto> {
        return registrationService.creditCard(cvc, dueDate)
    }

    @Operation(summary = "Create a debit card")
    @PostMapping("debit-card/{cvc}/{dueDate}")
    suspend fun debitCard(@PathVariable cvc: String, @PathVariable dueDate: LocalDate): ResponseEntity<CardResponseDto> {
        return registrationService.debitCard(cvc, dueDate)
    }

    @Operation(summary = "Delete a card")
    @DeleteMapping("{id}")
    suspend fun delete(@PathVariable id: String): ResponseEntity<Unit> {
        return deletionService.delete(id)
    }

    @Operation(summary = "Retrieve a card")
    @GetMapping
    suspend fun findAll(): ResponseEntity<Flow<CardResponseDto>> {
        return retrievalService.findAll()
    }

    @Operation(summary = "Retrieve a card by its id")
    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: String): ResponseEntity<CardResponseDto> {
        return retrievalService.findById(id)
    }

}