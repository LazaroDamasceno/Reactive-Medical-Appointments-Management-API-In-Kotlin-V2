package com.api.v1.cards.services

import com.api.v1.cards.domain.Card
import com.api.v1.cards.domain.CardRepository
import com.api.v1.cards.dtos.CardResponseDto
import com.api.v1.cards.utils.CardFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CardRetrievalServiceImpl(
    private val cardRepository: CardRepository,
    private val cardFinder: CardFinder
): CardRetrievalService {

    override suspend fun findAll(): ResponseEntity<Flow<CardResponseDto>> {
        return withContext(Dispatchers.IO) {
            val all = cardRepository
                .findAll()
                .map { c -> c.toDto() }
            ResponseEntity.ok(all)
        }
    }

    override suspend fun findById(id: String): ResponseEntity<CardResponseDto> {
        return withContext(Dispatchers.IO) {
            val foundCard = cardFinder.findById(id)
            val dto = foundCard.toDto()
            ResponseEntity.ok(dto)
        }
    }
}