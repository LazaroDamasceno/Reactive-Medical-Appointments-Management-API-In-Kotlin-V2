package com.api.v1.cards.services

import org.springframework.http.ResponseEntity

interface CardDeletionService {
    suspend fun delete(id: String): ResponseEntity<Unit>
}