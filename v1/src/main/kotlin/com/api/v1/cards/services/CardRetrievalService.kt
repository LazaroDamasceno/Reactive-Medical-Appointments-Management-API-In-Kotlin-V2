package com.api.v1.cards.services

import com.api.v1.cards.dtos.CardResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity

interface CardRetrievalService {
    suspend fun findAll(): ResponseEntity<Flow<CardResponseDto>>
    suspend fun findById(id: String): ResponseEntity<CardResponseDto>
}