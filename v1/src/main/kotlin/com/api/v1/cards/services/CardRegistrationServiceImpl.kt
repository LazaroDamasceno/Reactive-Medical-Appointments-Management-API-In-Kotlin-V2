package com.api.v1.cards.services

import com.api.v1.cards.domain.Card
import com.api.v1.cards.domain.CardRepository
import com.api.v1.cards.dtos.CardResponseDto
import com.api.v1.cards.enums.CardType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CardRegistrationServiceImpl(
    private val cardRepository: CardRepository
): CardRegistrationService {

    override suspend fun creditCard(cvc: String, dueDate: LocalDate): CardResponseDto {
        return withContext(Dispatchers.IO) {
            register(CardType.CREDIT_CARD, cvc, dueDate)
        }
    }

    override suspend fun debitCard(cvc: String, dueDate: LocalDate): CardResponseDto {
        return withContext(Dispatchers.IO) {
            register(CardType.DEBIT_CARD, cvc, dueDate)
        }
    }

    private suspend fun register(type: CardType, cvc: String, dueDate: LocalDate): CardResponseDto {
        return withContext(Dispatchers.IO) {
            val card = Card.of(type, cvc, dueDate)
            val savedCard = cardRepository.save(card)
            savedCard.toDto()
        }
    }
}