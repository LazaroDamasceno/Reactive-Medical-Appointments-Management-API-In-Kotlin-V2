package com.api.v2.cards.domain.exposed

import com.api.v2.cards.dtos.CardResponseDto
import com.api.v2.cards.enums.CardType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Document
data class Card (
    @Id
    val id: String,
    val type: CardType,
    val cvc: String,
    val dueDate: LocalDate,
    val createdAt: LocalDateTime
) {

    companion object {
        fun of(type: CardType, cvc: String, dueDate: LocalDate): Card {
            return Card(
                UUID.randomUUID().toString(),
                type,
                cvc,
                dueDate,
                LocalDateTime.now()
            )
        }
    }

    fun toDto(): CardResponseDto {
        return CardResponseDto(id, type, cvc, dueDate)
    }
}