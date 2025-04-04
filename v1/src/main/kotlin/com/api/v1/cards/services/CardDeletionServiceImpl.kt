package com.api.v1.cards.services

import com.api.v1.cards.domain.CardRepository
import com.api.v1.cards.utils.CardFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CardDeletionServiceImpl(
    private val cardRepository: CardRepository,
    private val cardFinder: CardFinder
): CardDeletionService {

    override suspend fun delete(id: String): ResponseEntity<Unit> {
        return withContext(Dispatchers.IO) {
            val foundCard = cardFinder.findById(id)
            cardRepository.delete(foundCard)
            ResponseEntity.noContent().build()
        }
    }
}