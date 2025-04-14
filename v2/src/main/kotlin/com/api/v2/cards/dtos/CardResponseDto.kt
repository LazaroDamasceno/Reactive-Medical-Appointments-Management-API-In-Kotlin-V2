package com.api.v2.cards.dtos

import com.api.v2.cards.enums.CardType
import java.time.LocalDate

data class CardResponseDto(
    val id: String,
    val type: CardType,
    val cvc: String,
    val dueDate: LocalDate
)
