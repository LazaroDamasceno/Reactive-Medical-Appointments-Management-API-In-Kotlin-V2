package com.api.v2.cards.services

import com.api.v2.cards.dtos.CardResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity

interface CardRetrievalService {
    suspend fun findAll(): ResponseEntity<Flow<CardResponseDto>>
    suspend fun findById(id: String): ResponseEntity<CardResponseDto>
}