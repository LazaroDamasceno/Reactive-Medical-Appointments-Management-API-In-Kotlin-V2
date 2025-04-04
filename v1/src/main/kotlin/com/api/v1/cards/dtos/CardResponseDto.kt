package com.api.v1.cards.dtos

import com.api.v1.cards.enums.CardType
import java.time.LocalDate

data class CardResponseDto(
    val id: String,
    val type: CardType,
    val cvc: String,
    val dueDate: LocalDate
)
