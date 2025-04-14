package com.api.v2.cards.utils

import com.api.v2.cards.domain.exposed.Card
import com.api.v2.cards.domain.CardRepository
import com.api.v2.cards.exceptions.NonExistentCardException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class CardFinder(
    private val cardRepository: CardRepository
) {

    suspend fun findById(id: String): Card {
        return withContext(Dispatchers.IO) {
            val foundCard = cardRepository.findById(id)
            if (foundCard == null) {
                throw NonExistentCardException(id)
            }
            foundCard
        }
    }

}